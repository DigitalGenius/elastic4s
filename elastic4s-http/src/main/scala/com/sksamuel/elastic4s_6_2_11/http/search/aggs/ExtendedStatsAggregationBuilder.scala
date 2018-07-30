package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.ExtendedStatsAggregationDefinition

object ExtendedStatsAggregationBuilder {
  def apply(agg: ExtendedStatsAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()

    builder.startObject("extended_stats")
    agg.field.foreach(builder.field("field", _))
    agg.sigma.foreach(builder.field("sigma", _))
    agg.missing.foreach(builder.field("missing", _))

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)

    builder
  }
}
