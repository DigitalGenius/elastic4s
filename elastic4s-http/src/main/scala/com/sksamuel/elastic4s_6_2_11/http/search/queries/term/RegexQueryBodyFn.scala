package com.sksamuel.elastic4s_6_2_11.http.search.queries.term

import com.sksamuel.elastic4s_6_2_11.http.EnumConversions
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.RegexQueryDefinition

object RegexQueryBodyFn {
  def apply(q: RegexQueryDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()

    builder.startObject("regexp")
    builder.startObject(q.field)
    builder.field("value", q.regex)
    if (q.flags.nonEmpty) {
      builder.field("flags", q.flags.map(EnumConversions.regexflag).mkString("|"))
    }
    q.maxDeterminedStates.foreach(builder.field("max_determinized_states", _))
    q.rewrite.foreach(builder.field("rewrite", _))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))

    builder
  }
}
