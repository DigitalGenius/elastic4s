package com.sksamuel.elastic4s_6_2_11.searches.queries

import org.elasticsearch.index.query.{ExistsQueryBuilder, QueryBuilders}

object ExistsQueryBuilderFn {
  def apply(q: ExistsQueryDefinition): ExistsQueryBuilder = {
    val builder = QueryBuilders.existsQuery(q.field)
    q.boost.map(_.toFloat).foreach(builder.boost)
    q.queryName.foreach(builder.queryName)
    builder
  }
}
