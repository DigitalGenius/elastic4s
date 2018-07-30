package com.sksamuel.elastic4s_6_2_11.searches.queries.span

import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import org.elasticsearch.index.query.{MultiTermQueryBuilder, QueryBuilders, SpanMultiTermQueryBuilder}

object SpanMultiTermQueryBuilder {
  def apply(q: SpanMultiTermQueryDefinition): SpanMultiTermQueryBuilder = {
    val builder = QueryBuilders.spanMultiTermQueryBuilder(QueryBuilderFn(q.query).asInstanceOf[MultiTermQueryBuilder])
    builder
  }
}
