package com.sksamuel.elastic4s_6_2_11.http.search

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.RawQueryDefinition

object RawQueryBodyFn {
  def apply(q: RawQueryDefinition): XContentBuilder = XContentFactory.parse(q.json)
}
