package com.sksamuel.elastic4s_6_2_11.http.search.queries.specialized

import com.sksamuel.elastic4s_6_2_11.http.ScriptBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.ScriptQueryDefinition

object ScriptQueryBodyFn {

  def apply(q: ScriptQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("script")
    builder.rawField("script", ScriptBuilderFn(q.script))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject()
    builder.endObject()
    builder
  }
}
