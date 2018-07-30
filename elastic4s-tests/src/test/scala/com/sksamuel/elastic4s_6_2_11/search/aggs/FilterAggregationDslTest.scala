package com.sksamuel.elastic4s_6_2_11.search.aggs

import com.sksamuel.elastic4s_6_2_11.ElasticApi
import com.sksamuel.elastic4s_6_2_11.searches.DateHistogramInterval
import org.scalatest.{FlatSpec, Matchers}

class FilterAggregationDslTest extends FlatSpec with Matchers with ElasticApi {

  "filter agg" should "support sub aggs" in {
    filterAggregation("filtered").query(
      filter(
        rangeQuery("some_date_field")
          .gte("now-1y")
      )
    ).addSubAggregation(
      dateHistogramAggregation("per_month")
        .field("some_date_field")
        .interval(DateHistogramInterval.Month)
        .minDocCount(0L)
    )
  }
}
