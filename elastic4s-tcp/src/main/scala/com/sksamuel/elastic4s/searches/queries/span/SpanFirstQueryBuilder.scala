package com.sksamuel.elastic4s_6_2_11.searches.queries.span

import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import org.elasticsearch.index.query.{QueryBuilders, SpanFirstQueryBuilder, SpanQueryBuilder}

object SpanFirstQueryBuilder {
  def apply(q: SpanFirstQueryDefinition): SpanFirstQueryBuilder = {
    val builder = QueryBuilders.spanFirstQuery(QueryBuilderFn(q).asInstanceOf[SpanQueryBuilder], q.end)
    builder
  }
}
