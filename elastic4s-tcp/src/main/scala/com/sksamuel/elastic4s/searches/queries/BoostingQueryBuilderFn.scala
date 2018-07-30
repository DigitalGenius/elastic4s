package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import org.elasticsearch.index.query.{BoostingQueryBuilder, QueryBuilders}

object BoostingQueryBuilderFn {
  def apply(q: BoostingQueryDefinition): BoostingQueryBuilder = {
    val builder = QueryBuilders.boostingQuery(QueryBuilderFn(q.positiveQuery), QueryBuilderFn(q.negativeQuery))
    q.boost.map(_.toFloat).foreach(builder.boost)
    q.negativeBoost.map(_.toFloat).foreach(builder.negativeBoost)
    q.queryName.foreach(builder.queryName)
    builder
  }
}
