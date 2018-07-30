package com.sksamuel.elastic4s_6_2_11.http.search.queries.term

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.term.TermQueryDefinition

object TermQueryBodyFn {

  def apply(t: TermQueryDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder().startObject("term")

    builder.startObject(t.field)
    t.boost.foreach(builder.field("boost", _))
    t.queryName.foreach(builder.field("_name", _))
    builder.autofield("value", t.value)

    builder.endObject().endObject().endObject()
  }
}
