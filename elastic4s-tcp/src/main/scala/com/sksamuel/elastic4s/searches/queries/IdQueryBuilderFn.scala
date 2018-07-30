package com.sksamuel.elastic4s_6_2_11.searches.queries

import org.elasticsearch.index.query.{IdsQueryBuilder, QueryBuilders}

object IdQueryBuilderFn {
  def apply(q: IdQuery): IdsQueryBuilder = {
    val builder = QueryBuilders.idsQuery(q.types: _*).addIds(q.ids.map(_.toString): _*)
    q.boost.foreach(b => builder.boost(b.toFloat))
    q.queryName.foreach(builder.queryName)
    builder
  }
}
