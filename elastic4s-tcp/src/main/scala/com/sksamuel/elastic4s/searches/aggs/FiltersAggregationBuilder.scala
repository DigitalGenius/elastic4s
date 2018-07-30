package com.sksamuel.elastic4s_6_2_11.searches.aggs

import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import org.elasticsearch.search.aggregations.AggregationBuilders
import org.elasticsearch.search.aggregations.bucket.filter.FiltersAggregationBuilder

import scala.collection.JavaConverters._

object FiltersAggregationBuilder {

  def apply(agg: FiltersAggregationDefinition): FiltersAggregationBuilder = {
    val builder = AggregationBuilders.filters(agg.name, agg.filters.map(QueryBuilderFn.apply).toSeq: _*)
    SubAggsFn(builder, agg.subaggs)
    if (agg.metadata.nonEmpty) builder.setMetaData(agg.metadata.asJava)
    builder
  }
}
