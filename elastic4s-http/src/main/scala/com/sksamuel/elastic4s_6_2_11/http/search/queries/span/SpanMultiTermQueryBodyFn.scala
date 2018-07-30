package com.sksamuel.elastic4s_6_2_11.http.search.queries.span

import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.span.SpanMultiTermQueryDefinition

object SpanMultiTermQueryBodyFn {
  def apply(q: SpanMultiTermQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("span_multi")
    builder.rawField("match", QueryBuilderFn(q.query))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject()
    builder.endObject()
  }
}
