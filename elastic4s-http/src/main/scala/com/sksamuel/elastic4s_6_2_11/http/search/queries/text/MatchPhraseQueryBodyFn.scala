package com.sksamuel.elastic4s_6_2_11.http.search.queries.text

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.matches.MatchPhraseDefinition

object MatchPhraseQueryBodyFn {
  def apply(q: MatchPhraseDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("match_phrase")
    builder.startObject(q.field)
    builder.autofield("query", q.value)
    q.analyzer.foreach(builder.field("analyzer", _))
    q.slop.foreach(builder.field("slop", _))
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject()
    builder.endObject()
    builder.endObject()
    builder
  }
}
