package com.sksamuel.elastic4s_6_2_11.http.count

import com.sksamuel.elastic4s_6_2_11.count.CountDefinition
import com.sksamuel.elastic4s_6_2_11.http.search.queries.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}

object CountBodyBuilderFn {
  def apply(request: CountDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    request.query.map(QueryBuilderFn.apply).foreach(builder.rawField("query", _))
    builder
  }
}
