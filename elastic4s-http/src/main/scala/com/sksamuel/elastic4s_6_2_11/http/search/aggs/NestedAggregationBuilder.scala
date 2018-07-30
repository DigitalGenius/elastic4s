package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.json.XContentBuilder
import com.sksamuel.elastic4s_6_2_11.json.XContentFactory
import com.sksamuel.elastic4s_6_2_11.searches.aggs.NestedAggregationDefinition

object NestedAggregationBuilder {
  def apply(agg: NestedAggregationDefinition): XContentBuilder = {
    val builder = XContentFactory.obj().startObject("nested")
    builder.field("path", agg.path)
    builder.endObject()
    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder
  }
}
