package com.sksamuel.elastic4s_6_2_11.search.queries

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class NestedQueryTest extends WordSpec with DockerTests with Matchers {

  Try {
    http.execute {
      deleteIndex("nested")
    }.await
  }

  http.execute {
    createIndex("nested").mappings(
      mapping("places").fields(
        keywordField("name"),
        nestedField("states")
      )
    )
  }

  http.execute(
    bulk(
      indexInto("nested" / "places") fields(
        "name" -> "usa",
        "states" -> Seq(
          Map(
            "name" -> "Montana",
            "capital" -> "Helena",
            "entry" -> 1889
          ), Map(
            "name" -> "South Dakota",
            "capital" -> "Pierre",
            "entry" -> 1889
          )
        )
      ),
      indexInto("nested" / "places") fields(
        "name" -> "fictional usa",
        "states" -> Seq(
          Map(
            "name" -> "Old Jersey",
            "capital" -> "Trenton",
            "entry" -> 1889
          ), Map(
            "name" -> "Montana",
            "capital" -> "Helena",
            "entry" -> 1567
          )
        )
      )
    ).refresh(RefreshPolicy.Immediate)
  ).await

  "nested query" should {
    "match against nested objects" in {
      http.execute {
        search("nested") query {
          nestedQuery("states").query {
            boolQuery.must(
              matchQuery("states.name", "Montana"),
              matchQuery("states.entry", 1889)
            )
          }
        }
      }.await.right.get.result.totalHits shouldBe 1
    }
  }
}
