package com.sksamuel.elastic4s_6_2_11

trait ExistsApi {
  def exists(id: String, index: Index, `type`: String): ExistsDefinition = ExistsDefinition(id, index, `type`)
}

case class ExistsDefinition(id: String, index: Index, `type`: String)
