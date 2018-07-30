package com.sksamuel.elastic4s_6_2_11.http.explain

import com.sksamuel.elastic4s_6_2_11.explain.ExplainDefinition
import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}

object ExplainBodyFn {
  def apply(v: ExplainDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.rawField("query", QueryBuilderFn(v.query.get))
    builder.endObject()
  }
}
