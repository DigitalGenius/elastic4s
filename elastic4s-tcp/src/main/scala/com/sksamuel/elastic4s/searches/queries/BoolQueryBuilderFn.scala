package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import org.elasticsearch.index.query.{BoolQueryBuilder, QueryBuilders}

object BoolQueryBuilderFn {
  def apply(q: BoolQueryDefinition): BoolQueryBuilder = {
    val builder = QueryBuilders.boolQuery()
    q.adjustPureNegative.foreach(builder.adjustPureNegative)
    q.minimumShouldMatch.foreach(builder.minimumShouldMatch)
    q.queryName.foreach(builder.queryName)
    q.boost.map(_.toFloat).foreach(builder.boost)
    q.must.map(QueryBuilderFn.apply).foreach(builder.must)
    q.filters.map(QueryBuilderFn.apply).foreach(builder.filter)
    q.not.map(QueryBuilderFn.apply).foreach(builder.mustNot)
    q.should.map(QueryBuilderFn.apply).foreach(builder.should)
    builder
  }
}
