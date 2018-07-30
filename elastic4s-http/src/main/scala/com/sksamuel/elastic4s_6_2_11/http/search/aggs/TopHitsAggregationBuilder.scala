package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.http.FetchSourceContextBuilderFn
import com.sksamuel.elastic4s_6_2_11.http.search.queries.SortBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.TopHitsAggregationDefinition

object TopHitsAggregationBuilder {

  def apply(agg: TopHitsAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder().startObject("top_hits")

    agg.size.foreach(builder.field("size", _))
    agg.from.foreach(builder.field("from", _))
    if (agg.sorts.nonEmpty) {
      builder.startArray("sort")
      agg.sorts.foreach { sort =>
        builder.rawValue(SortBuilderFn(sort))
      }
      builder.endArray()
    }

    // source filtering
    agg.fetchSource.foreach(FetchSourceContextBuilderFn(builder, _))

    agg.explain.foreach(builder.field("explain", _))
    agg.version.foreach(builder.field("version", _))

    builder.endObject().endObject()
  }
}
