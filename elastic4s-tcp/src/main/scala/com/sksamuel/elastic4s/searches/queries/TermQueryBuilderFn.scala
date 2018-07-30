package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.elastic4s_6_2_11.searches.queries.term.TermQueryDefinition
import org.elasticsearch.index.query.{QueryBuilders, TermQueryBuilder}

object TermQueryBuilderFn {
  def apply(q: TermQueryDefinition): TermQueryBuilder = {
    val builder = q.value match {
      case str: String         => QueryBuilders.termQuery(q.field, str)
      case iter: Iterable[Any] => QueryBuilders.termQuery(q.field, iter.toArray)
      case other               => QueryBuilders.termQuery(q.field, other)
    }
    q.boost.map(_.toFloat).foreach(builder.boost)
    q.queryName.foreach(builder.queryName)
    builder
  }
}
