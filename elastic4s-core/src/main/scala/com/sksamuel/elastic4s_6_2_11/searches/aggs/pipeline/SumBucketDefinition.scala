package com.sksamuel.elastic4s_6_2_11.searches.aggs.pipeline

case class SumBucketDefinition(name: String,
                               bucketsPath: String,
                               format: Option[String] = None,
                               gapPolicy: Option[GapPolicy] = None,
                               metadata: Map[String, AnyRef] = Map.empty)
    extends PipelineAggregationDefinition {

  type T = SumBucketDefinition

  def format(format: String): SumBucketDefinition                  = copy(format = Some(format))
  def gapPolicy(gapPolicy: GapPolicy): SumBucketDefinition         = copy(gapPolicy = Some(gapPolicy))
  def metadata(metadata: Map[String, AnyRef]): SumBucketDefinition = copy(metadata = metadata)
}
