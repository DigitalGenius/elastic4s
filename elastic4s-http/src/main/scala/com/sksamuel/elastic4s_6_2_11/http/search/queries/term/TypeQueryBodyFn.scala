package com.sksamuel.elastic4s_6_2_11.http.search.queries.term

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.TypeQueryDefinition

object TypeQueryBodyFn {
  def apply(q: TypeQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("type")
    builder.field("value", q.`type`)
    builder.endObject().endObject()
  }
}
