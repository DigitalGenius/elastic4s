package com.sksamuel.elastic4s_6_2_11.termvectors

import com.sksamuel.elastic4s_6_2_11.{Index, IndexAndType}

trait TermVectorApi {
  def termVectors(index: Index, `type`: String, id: String): TermVectorsDefinition =
    TermVectorsDefinition(IndexAndType(index.name, `type`), id.toString)
}
