package com.sksamuel.elastic4s_6_2_11.searches.aggs

import com.sksamuel.elastic4s_6_2_11.{EnumConversions, ScriptBuilder}
import com.sksamuel.elastic4s_6_2_11.script.SortBuilderFn
import com.sksamuel.elastic4s_6_2_11.searches.sort.SortDefinition
import org.elasticsearch.search.aggregations.AggregationBuilders
import org.elasticsearch.search.aggregations.metrics.tophits.TopHitsAggregationBuilder
import org.elasticsearch.search.sort.SortBuilder

import scala.collection.JavaConverters._

object TopHitsAggregationBuilder {
  def apply(agg: TopHitsAggregationDefinition): TopHitsAggregationBuilder = {

    val builder = AggregationBuilders.topHits(agg.name)
    agg.explain.foreach(builder.explain)
    agg.fetchSource.map(EnumConversions.fetchSource).foreach(builder.fetchSource)
    agg.trackScores.foreach(builder.trackScores)
    agg.version.foreach(builder.version)
    agg.size.foreach(builder.size)
    agg.from.foreach(builder.from)
    agg.storedFields.foreach(builder.storedField)

    agg.scripts.foreach {
      case (name, script) =>
        builder.scriptField(name, ScriptBuilder(script))
    }

    def addSort[T <: SortBuilder[T]](sort: SortDefinition) = builder.sort(SortBuilderFn(sort).asInstanceOf[T])
    agg.sorts.foreach(addSort)

    SubAggsFn(builder, agg.subaggs)
    if (agg.metadata.nonEmpty) builder.setMetaData(agg.metadata.asJava)
    builder
  }
}
