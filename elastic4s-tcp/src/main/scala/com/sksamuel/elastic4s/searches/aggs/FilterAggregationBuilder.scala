package com.sksamuel.elastic4s_6_2_11.searches.aggs

import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import org.elasticsearch.search.aggregations.AggregationBuilders
import org.elasticsearch.search.aggregations.bucket.filter.FilterAggregationBuilder

import scala.collection.JavaConverters._

object FilterAggregationBuilder {

  def apply(agg: FilterAggregationDefinition): FilterAggregationBuilder = {
    val builder = AggregationBuilders.filter(agg.name, QueryBuilderFn(agg.query))
    SubAggsFn(builder, agg.subaggs)
    if (agg.metadata.nonEmpty) builder.setMetaData(agg.metadata.asJava)
    builder
  }
}
