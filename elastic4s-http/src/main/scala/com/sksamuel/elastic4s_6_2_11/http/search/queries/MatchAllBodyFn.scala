package com.sksamuel.elastic4s_6_2_11.http.search.queries

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.matches.{MatchAllQueryDefinition, MatchNoneQueryDefinition}

object MatchAllBodyFn {
  def apply(q: MatchAllQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("match_all")
    q.boost.foreach(builder.field("boost", _))
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject().endObject()
  }
}

object MatchNoneBodyFn {

  def apply(q: MatchNoneQueryDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.startObject("match_none")
    q.queryName.foreach(builder.field("_name", _))
    builder.endObject().endObject()
  }
}
