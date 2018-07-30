package com.sksamuel.elastic4s_6_2_11.search.aggs

import com.sksamuel.elastic4s_6_2_11.searches.DateHistogramInterval
import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{FreeSpec, Matchers}

import scala.util.Try

class AvgBucketPipelineAggHttpTest extends FreeSpec with DockerTests with Matchers {

  Try {
    http.execute {
      deleteIndex("avgbucketagg")
    }.await
  }

  http.execute {
    createIndex("avgbucketagg") mappings {
      mapping("sales") fields(
        dateField("date"),
        doubleField("value").stored(true)
      )
    }
  }.await

  http.execute(
    bulk(
      indexInto("avgbucketagg/sales") fields("date" -> "2017-01-01", "value" -> 1000.0),
      indexInto("avgbucketagg/sales") fields("date" -> "2017-01-02", "value" -> 1000.0),
      indexInto("avgbucketagg/sales") fields("date" -> "2017-02-01", "value" -> 2000.0),
      indexInto("avgbucketagg/sales") fields("date" -> "2017-02-01", "value" -> 2000.0),
      indexInto("avgbucketagg/sales") fields("date" -> "2017-03-01", "value" -> 3000.0),
      indexInto("avgbucketagg/sales") fields("date" -> "2017-03-02", "value" -> 3000.0)
    ).refresh(RefreshPolicy.Immediate)
  ).await

  "avg bucket pipeline agg" - {
    "should return the expected avg value" in {

      val resp = http.execute {
        search("avgbucketagg").matchAllQuery().aggs(
          dateHistogramAgg("sales_per_month", "date")
            .interval(DateHistogramInterval.Month)
            .subaggs {
              sumAgg("sales", "value")
            },

          avgBucketAgg("avg_monthly_sales", "sales_per_month>sales")
        )
      }.await.right.get.result

      resp.totalHits shouldBe 6

      val agg = resp.aggs.avgBucket("avg_monthly_sales")
      agg.value shouldBe 4000.0
    }
  }
}
