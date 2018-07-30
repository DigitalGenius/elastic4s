package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.elastic4s_6_2_11.ScriptBuilder
import org.elasticsearch.index.query.{QueryBuilders, ScriptQueryBuilder}

object ScriptQueryBuilderFn {
  def apply(q: ScriptQueryDefinition): ScriptQueryBuilder = {
    val builder = QueryBuilders.scriptQuery(ScriptBuilder(q.script))
    q.boost.map(_.toFloat).foreach(builder.boost)
    q.queryName.foreach(builder.queryName)
    builder
  }
}
