package com.sksamuel.elastic4s_6_2_11.validate

import com.sksamuel.elastic4s_6_2_11.IndexesAndTypes
import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition

trait ValidateApi {

  def validateIn(indexesAndTypes: IndexesAndTypes): ValidateExpectsQuery = new ValidateExpectsQuery(indexesAndTypes)
  class ValidateExpectsQuery(indexesAndTypes: IndexesAndTypes) {
    def query(query: QueryDefinition): ValidateDefinition = ValidateDefinition(indexesAndTypes, query)
  }
}
