package com.sksamuel.elastic4s_6_2_11.bulk

import com.sksamuel.elastic4s_6_2_11.indexes.IndexApi

import scala.language.implicitConversions

trait BulkApi {
  this: IndexApi =>

  def bulk(requests: Iterable[BulkCompatibleDefinition]): BulkDefinition = BulkDefinition(requests.toSeq)
  def bulk(requests: BulkCompatibleDefinition*): BulkDefinition          = bulk(requests)
}
