package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.GlobalAggregationDefinition

object GlobalAggregationBuilder {
  def apply(agg: GlobalAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.obj.startObject("global")

    builder.endObject()

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
