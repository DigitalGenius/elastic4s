package com.sksamuel.elastic4s_6_2_11.http.search.aggs.pipeline

import com.sksamuel.elastic4s_6_2_11.http.search.aggs.AggMetaDataFn
import com.sksamuel.elastic4s_6_2_11.http.search.queries.SortBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.pipeline.BucketSortDefinition

object BucketSortPipelineAggBuilder {
  def apply(agg: BucketSortDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("bucket_sort")
    if (agg.sort.nonEmpty) {
      builder.startArray("sort")
      agg.sort.foreach { sort =>
        builder.rawValue(SortBuilderFn(sort))
      }
      builder.endArray()
    }
    agg.from.foreach(builder.field("from", _))
    agg.size.foreach(builder.field("size", _))
    agg.gapPolicy.foreach(policy => builder.field("gap_policy", policy.toString.toLowerCase))
    builder.endObject()
    AggMetaDataFn(agg, builder)
    builder.endObject()
  }
}
