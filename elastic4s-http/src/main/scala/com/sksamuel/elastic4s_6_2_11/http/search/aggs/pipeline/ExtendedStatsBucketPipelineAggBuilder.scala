package com.sksamuel.elastic4s_6_2_11.http.search.aggs.pipeline

import com.sksamuel.elastic4s_6_2_11.http.search.aggs.AggMetaDataFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.pipeline.ExtendedStatsBucketDefinition

object ExtendedStatsBucketPipelineAggBuilder {
  def apply(agg: ExtendedStatsBucketDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("extended_stats_bucket")
    builder.field("buckets_path", agg.bucketsPath)
    agg.gapPolicy.foreach(policy => builder.field("gap_policy", policy.toString.toLowerCase))
    agg.format.foreach(f => builder.field("format", f))
    builder.endObject()
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
