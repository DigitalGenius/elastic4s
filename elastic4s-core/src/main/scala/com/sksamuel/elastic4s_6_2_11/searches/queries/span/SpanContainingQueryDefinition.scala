package com.sksamuel.elastic4s_6_2_11.searches.queries.span

import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition
import com.sksamuel.exts.OptionImplicits._

case class SpanContainingQueryDefinition(little: SpanQueryDefinition,
                                         big: SpanQueryDefinition,
                                         boost: Option[Double] = None,
                                         queryName: Option[String] = None)
    extends QueryDefinition {

  def boost(boost: Double): SpanContainingQueryDefinition         = copy(boost = boost.some)
  def queryName(queryName: String): SpanContainingQueryDefinition = copy(queryName = queryName.some)
}
