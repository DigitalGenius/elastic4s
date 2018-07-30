package com.sksamuel.elastic4s_6_2_11.searches.queries

import org.elasticsearch.index.query.{QueryBuilders, WrapperQueryBuilder}

object RawQueryBuilderFn {
  def apply(q: RawQueryDefinition): WrapperQueryBuilder = QueryBuilders.wrapperQuery(q.json)
}
