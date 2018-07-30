package com.sksamuel.elastic4s_6_2_11.http.search.queries.term

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.WildcardQueryDefinition

object WildcardQueryBodyFn {
  def apply(q: WildcardQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("wildcard").startObject(q.field)
    builder.autofield("value", q.query)
    q.rewrite.foreach(builder.field("rewrite", _))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject().endObject().endObject()
  }
}
