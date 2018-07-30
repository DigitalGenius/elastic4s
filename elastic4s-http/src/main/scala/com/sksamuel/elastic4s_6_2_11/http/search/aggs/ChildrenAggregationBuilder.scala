package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.ChildrenAggregationDefinition

object ChildrenAggregationBuilder {
  def apply(agg: ChildrenAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder().startObject("children")

    builder.field("type", agg.childType)
    builder.endObject()

    SubAggsBuilderFn(agg, builder)

    builder
  }
}
