package com.sksamuel.elastic4s_6_2_11.http.search.queries.term

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.term.TermsQueryDefinition

object TermsQueryBodyFn {
  def apply(t: TermsQueryDefinition[_]): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder().startObject("terms")

    if (t.ref.nonEmpty) {
      builder.startObject(t.field)
      t.ref.foreach { ref =>
        builder.field("index", ref.index)
        builder.field("type", ref.`type`)
        builder.field("id", ref.id)
      }
      t.path.foreach(builder.field("path", _))
      t.routing.foreach(builder.field("routing", _))
      builder.endObject()
    } else {
      builder.startArray(t.field)
      t.values.foreach(builder.autovalue)
      builder.endArray()
    }

    t.boost.foreach(builder.field("boost", _))
    t.queryName.foreach(builder.field("_name", _))

    builder.endObject().endObject()
  }
}
