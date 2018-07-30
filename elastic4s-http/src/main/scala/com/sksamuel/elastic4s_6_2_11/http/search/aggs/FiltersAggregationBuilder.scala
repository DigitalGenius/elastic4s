package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.FiltersAggregationDefinition

object FiltersAggregationBuilder {
  def apply(agg: FiltersAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()

    val filters = {
      builder.startArray("filters")
      val filters = agg.filters.map(QueryBuilderFn.apply).map(_.string).mkString(",")
      builder.rawValue(filters)
      builder.endArray()
    }

    builder.rawField("filters", filters)

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)
    builder
  }
}
