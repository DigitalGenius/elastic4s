package com.sksamuel.elastic4s_6_2_11.searches.queries.span

import com.sksamuel.elastic4s_6_2_11.searches.queries.{MultiTermQueryDefinition, QueryDefinition}
import com.sksamuel.exts.OptionImplicits._

trait SpanQueryDefinition extends QueryDefinition

case class SpanMultiTermQueryDefinition(query: MultiTermQueryDefinition,
                                        boost: Option[Double] = None,
                                        queryName: Option[String] = None)
    extends SpanQueryDefinition {

  def boost(boost: Double): SpanMultiTermQueryDefinition         = copy(boost = boost.some)
  def queryName(queryName: String): SpanMultiTermQueryDefinition = copy(queryName = queryName.some)
}

case class SpanFirstQueryDefinition(query: SpanQueryDefinition,
                                    end: Int,
                                    boost: Option[Double] = None,
                                    queryName: Option[String] = None)
    extends QueryDefinition {

  def boost(boost: Double): SpanFirstQueryDefinition         = copy(boost = boost.some)
  def queryName(queryName: String): SpanFirstQueryDefinition = copy(queryName = queryName.some)
}
