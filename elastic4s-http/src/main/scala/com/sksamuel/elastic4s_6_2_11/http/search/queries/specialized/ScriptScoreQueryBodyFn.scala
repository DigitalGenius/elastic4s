package com.sksamuel.elastic4s_6_2_11.http.search.queries.specialized

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer.ScriptScoreDefinition

object ScriptScoreQueryBodyFn {

  def apply(q: ScriptScoreDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.rawField("script", q.script.script)
    builder
  }

}
