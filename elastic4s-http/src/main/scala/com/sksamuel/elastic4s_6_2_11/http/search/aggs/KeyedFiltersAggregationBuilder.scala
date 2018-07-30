package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.KeyedFiltersAggregationDefinition

object KeyedFiltersAggregationBuilder {
  def apply(agg: KeyedFiltersAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()

    builder.startObject("filters")
    agg.otherBucket.foreach(builder.field("other_bucket", _))
    agg.otherBucketKey.foreach(builder.field("other_bucket_key", _))

    builder.startObject("filters")
    agg.filters.map(map => {
      builder.rawField(map._1, QueryBuilderFn(map._2))
    })
    builder.endObject()

    builder.endObject()

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder
  }
}
