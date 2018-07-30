package com.sksamuel.elastic4s_6_2_11

import com.sksamuel.elastic4s_6_2_11.indexes.IndexDefinition
import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition

trait PercolateDsl {
  self: ElasticDsl =>

  def register(query: QueryDefinition) = new RegisterExpectsInto(query)
  class RegisterExpectsInto(query: QueryDefinition) {
    def into(indexType: IndexAndType, field: String = "query"): IndexDefinition = {
      val src = s""" { "$field" : ${QueryBuilderFn(query).toString} } """
      indexInto(indexType).source(src)
    }
  }
}
