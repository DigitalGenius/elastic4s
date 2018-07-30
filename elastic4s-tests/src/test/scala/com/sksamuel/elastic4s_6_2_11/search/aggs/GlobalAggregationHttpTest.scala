package com.sksamuel.elastic4s_6_2_11.search.aggs

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{FreeSpec, Matchers}

import scala.util.Try

class GlobalAggregationHttpTest extends FreeSpec with DockerTests with Matchers {

  Try {
    http.execute {
      deleteIndex("globalagg")
    }.await
  }

  http.execute {
    createIndex("globalagg") mappings {
      mapping("colors") fields keywordField("name")
    }
  }.await

  http.execute(
    bulk(
      indexInto("globalagg/colors") fields("name" -> "cyan"),
      indexInto("globalagg/colors") fields("name" -> "magenta"),
      indexInto("globalagg/colors") fields("name" -> "yellow"),
      indexInto("globalagg/colors") fields("name" -> "black"),
      indexInto("globalagg/colors") fields("name" -> "black")
    ).refresh(RefreshPolicy.Immediate)
  ).await

  "global agg" - {
    "should be not influenced by the search query" in {

      val resp = http.execute {
        search("globalagg").termQuery("name", "black").aggs {
          globalAggregation("global")
        }
      }.await.right.get.result

      resp.totalHits shouldBe 2
      resp.aggs.global("global").docCount shouldBe 5
    }

    "should allow to use subaggregations" in {

      val resp = http.execute {
        search("globalagg").termQuery("name", "yellow").aggs {
          globalAggregation("global").subaggs {
            filterAgg("blackAgg", termQuery("name", "black"))
          }
        }
      }.await.right.get.result

      resp.totalHits shouldBe 1
      resp.aggs.global("global").docCount shouldBe 5
      resp.aggs.global("global").filter("blackAgg").docCount shouldBe 2
    }

  }
}
