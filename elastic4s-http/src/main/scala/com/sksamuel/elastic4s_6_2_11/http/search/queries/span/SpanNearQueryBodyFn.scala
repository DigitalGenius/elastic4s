package com.sksamuel.elastic4s_6_2_11.http.search.queries.span

import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.span.SpanNearQueryDefinition

object SpanNearQueryBodyFn {
  def apply(q: SpanNearQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()

    builder.startObject("span_near")
    builder.startArray("clauses")
    q.clauses.foreach { clause =>
      builder.rawValue(QueryBuilderFn(clause))
    }
    builder.endArray()

    builder.field("slop", q.slop)
    q.inOrder.foreach(builder.field("in_order", _))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))

    builder.endObject()
    builder.endObject()
  }
}
