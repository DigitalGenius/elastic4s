package com.sksamuel.elastic4s_6_2_11.searches

import org.elasticsearch.action.search.ClearScrollResponse

case class ClearScrollResult(response: ClearScrollResponse) {
  def number: Int      = response.getNumFreed
  def success: Boolean = response.isSucceeded
}
