package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.elastic4s_6_2_11.searches.queries.term.TermsQueryDefinition
import org.elasticsearch.index.query.TermsQueryBuilder

object TermsQueryBuilderFn {
  def apply[T](q: TermsQueryDefinition[T]): TermsQueryBuilder = {
    val builder = q.buildable.build(q).asInstanceOf[TermsQueryBuilder]
    q.queryName.foreach(builder.queryName)
    q.boost.map(_.toFloat).foreach(builder.boost)
    builder
  }
}
