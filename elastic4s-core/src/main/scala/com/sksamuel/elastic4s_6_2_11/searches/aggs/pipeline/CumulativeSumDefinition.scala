package com.sksamuel.elastic4s_6_2_11.searches.aggs.pipeline

case class CumulativeSumDefinition(name: String,
                                   bucketsPath: String,
                                   format: Option[String] = None,
                                   metadata: Map[String, AnyRef] = Map.empty)
    extends PipelineAggregationDefinition {

  type T = CumulativeSumDefinition

  def format(format: String): CumulativeSumDefinition                  = copy(format = Some(format))
  def metadata(metadata: Map[String, AnyRef]): CumulativeSumDefinition = copy(metadata = metadata)
}
