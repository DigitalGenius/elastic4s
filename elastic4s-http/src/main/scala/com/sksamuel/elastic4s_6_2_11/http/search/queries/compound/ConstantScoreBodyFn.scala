package com.sksamuel.elastic4s_6_2_11.http.search.queries.compound

import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.ConstantScoreDefinition

object ConstantScoreBodyFn {
  def apply(q: ConstantScoreDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("constant_score")
    builder.rawField("filter", QueryBuilderFn(q.query))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject()
    builder.endObject()
  }
}
