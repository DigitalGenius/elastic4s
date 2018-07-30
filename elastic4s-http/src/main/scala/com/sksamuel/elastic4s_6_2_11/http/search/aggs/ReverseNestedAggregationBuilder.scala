package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.json.XContentBuilder
import com.sksamuel.elastic4s_6_2_11.json.XContentFactory
import com.sksamuel.elastic4s_6_2_11.searches.aggs.ReverseNestedAggregationDefinition

object ReverseNestedAggregationBuilder {
  def apply(agg: ReverseNestedAggregationDefinition): XContentBuilder = {
    val builder = XContentFactory.obj().startObject("reverse_nested")
    agg.path.foreach(builder.field("path", _))
    builder.endObject()
    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
