package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.GeoHashGridAggregationDefinition

object GeoHashGridAggregationBuilder {
  def apply(agg: GeoHashGridAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.obj.startObject("geohash_grid")

    agg.field.foreach(builder.field("field", _))
    agg.precision.foreach(builder.field("precision", _))
    agg.size.foreach(builder.field("size", _))
    agg.shardSize.foreach(builder.field("shard_size", _))

    builder.endObject()

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
