package com.sksamuel.elastic4s_6_2_11.update

import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition
import com.sksamuel.elastic4s_6_2_11.{Index, IndexAndType, Indexes, IndexesAndTypes}

trait UpdateApi {

  def updateById(index: Index, `type`: String, id: String) = UpdateDefinition(IndexAndType(index.name, `type`), id)
  def updateByQuery(index: Index, `type`: String, query: QueryDefinition) =
    UpdateByQueryDefinition(IndexAndType(index.name, `type`), query)

  def update(id: String): UpdateExpectsIn = new UpdateExpectsIn(id)
  class UpdateExpectsIn(id: String) {
    def in(indexType: IndexAndType): UpdateDefinition = UpdateDefinition(indexType, id)
  }

  def updateIn(indexes: Indexes): UpdateExpectsQuery = new UpdateExpectsQuery(indexes.toIndexesAndTypes)

  def updateIn(indexesAndTypes: IndexesAndTypes): UpdateExpectsQuery = new UpdateExpectsQuery(indexesAndTypes)

  class UpdateExpectsQuery(indexesAndTypes: IndexesAndTypes) {
    def query(query: QueryDefinition) = UpdateByQueryDefinition(indexesAndTypes, query)
  }
}
