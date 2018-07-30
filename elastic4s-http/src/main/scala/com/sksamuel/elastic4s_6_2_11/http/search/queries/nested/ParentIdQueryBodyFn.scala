package com.sksamuel.elastic4s_6_2_11.http.search.queries.nested

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.ParentIdQueryDefinition

object ParentIdQueryBodyFn {
  def apply(q: ParentIdQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder().startObject("parent_id")
    builder.field("type", q.`type`)
    builder.field("id", q.id)
    q.ignoreUnmapped.foreach(builder.field("ignore_unmapped", _))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject().endObject()
  }
}
