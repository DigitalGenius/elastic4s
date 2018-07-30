package com.sksamuel.elastic4s_6_2_11.http.search.queries.span

import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.span.SpanOrQueryDefinition

object SpanOrQueryBodyFn {
  def apply(q: SpanOrQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("span_or")
    builder.startArray("clauses")
    q.clauses.foreach { clause =>
      builder.rawValue(QueryBuilderFn(clause))
    }
    builder.endArray()

    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))

    builder.endObject()
    builder.endObject()
  }
}
