package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import org.elasticsearch.index.query.{ConstantScoreQueryBuilder, QueryBuilders}

object ConstantScoreBuilderF {
  def apply(q: ConstantScoreDefinition): ConstantScoreQueryBuilder = {
    val builder = QueryBuilders.constantScoreQuery(QueryBuilderFn(q.query))
    q.queryName.foreach(builder.queryName)
    q.boost.map(_.toFloat).foreach(builder.boost)
    builder
  }
}
