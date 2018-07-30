package com.sksamuel.elastic4s_6_2_11.searches.aggs.pipeline

case class MinBucketDefinition(name: String,
                               bucketsPath: String,
                               format: Option[String] = None,
                               gapPolicy: Option[GapPolicy] = None,
                               metadata: Map[String, AnyRef] = Map.empty)
    extends PipelineAggregationDefinition {

  type T = MinBucketDefinition

  def format(format: String): MinBucketDefinition                  = copy(format = Some(format))
  def gapPolicy(gapPolicy: GapPolicy): MinBucketDefinition         = copy(gapPolicy = Some(gapPolicy))
  def metadata(metadata: Map[String, AnyRef]): MinBucketDefinition = copy(metadata = metadata)
}
