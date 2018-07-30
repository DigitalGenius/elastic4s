package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.http.ScriptBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.GeoCentroidAggregationDefinition

object GeoCentroidAggregationBuilder {
  def apply(agg: GeoCentroidAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.obj.startObject("geo_centroid")

    agg.field.foreach(builder.field("field", _))
    agg.format.foreach(builder.field("format", _))
    agg.missing.foreach(builder.autofield("missing", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }

    builder.endObject()

    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
