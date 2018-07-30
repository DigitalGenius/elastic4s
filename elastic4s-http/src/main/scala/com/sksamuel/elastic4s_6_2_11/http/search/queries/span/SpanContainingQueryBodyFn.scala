package com.sksamuel.elastic4s_6_2_11.http.search.queries.span

import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.span.SpanContainingQueryDefinition

object SpanContainingQueryBodyFn {

  def apply(q: SpanContainingQueryDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()
    builder.startObject("span_containing")

    builder.rawField("little", QueryBuilderFn(q.little))
    builder.rawField("big", QueryBuilderFn(q.big))

    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))

    builder.endObject()
    builder.endObject()
  }
}
