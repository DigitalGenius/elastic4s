package com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer

import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition

trait ScoreFunctionDefinition {
  def filter: Option[QueryDefinition]
}
