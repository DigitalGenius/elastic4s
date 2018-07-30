package com.sksamuel.elastic4s_6_2_11.searches.aggs.pipeline

import com.sksamuel.elastic4s_6_2_11.EnumConversions
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders
import org.elasticsearch.search.aggregations.pipeline.movavg.MovAvgPipelineAggregationBuilder

import scala.collection.JavaConverters._

object MovAvgPipelineBuilder {

  def apply(p: MovAvgDefinition): MovAvgPipelineAggregationBuilder = {
    val builder = PipelineAggregatorBuilders.movingAvg(p.name, p.bucketsPath)
    if (p.metadata.nonEmpty) builder.setMetaData(p.metadata.asJava)
    p.format.foreach(builder.format)
    p.gapPolicy.map(EnumConversions.gapPolicy).foreach(builder.gapPolicy)
    p.minimize.foreach(builder.minimize)
    // p.modelBuilder.map(EnumConversions.modelBuilder).foreach(builder.modelBuilder)
    p.numPredictions.foreach(num => builder.predict(num))
    p.window.foreach(win => builder.window(win))
    builder
  }
}
