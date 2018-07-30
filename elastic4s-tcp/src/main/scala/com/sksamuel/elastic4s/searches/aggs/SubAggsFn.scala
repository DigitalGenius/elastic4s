package com.sksamuel.elastic4s_6_2_11.searches.aggs

import org.elasticsearch.search.aggregations.AggregationBuilder

object SubAggsFn {
  def apply(builder: AggregationBuilder, subaggs: Seq[AbstractAggregation]): Unit =
    subaggs.map(AggregationBuilderFn.apply).foreach {
      case Left(agg)   => builder.subAggregation(agg)
      case Right(pipe) => builder.subAggregation(pipe)
    }
}
