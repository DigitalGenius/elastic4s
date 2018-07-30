package com.sksamuel.elastic4s_6_2_11.http.search.queries.term

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.IdQuery

object IdQueryBodyFn {

  def apply(q: IdQuery): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("ids")
    if (q.types.nonEmpty) {
      builder.array("type", q.types.toArray)
    }
    builder.autoarray("values", q.ids)
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject()
    builder.endObject()
    builder
  }
}
