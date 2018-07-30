package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.http.ScriptBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.CardinalityAggregationDefinition

object CardinalityAggregationBuilder {
  def apply(agg: CardinalityAggregationDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder().startObject("cardinality")
    agg.field.foreach(builder.field("field", _))
    agg.missing.foreach(builder.field("missing", _))
    agg.precisionThreshold.foreach(builder.field("precision_threshold", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }
    builder.endObject().endObject()
  }
}
