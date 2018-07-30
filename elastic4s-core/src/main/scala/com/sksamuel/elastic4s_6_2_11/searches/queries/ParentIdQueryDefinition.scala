package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.exts.OptionImplicits._

case class ParentIdQueryDefinition(`type`: String,
                                   id: String,
                                   ignoreUnmapped: Option[Boolean] = None,
                                   boost: Option[Double] = None,
                                   queryName: Option[String] = None)
    extends QueryDefinition {

  def queryName(name: String): ParentIdQueryDefinition         = copy(queryName = name.some)
  def boost(boost: Double): ParentIdQueryDefinition            = copy(boost = boost.some)
  def ignoreUnmapped(ignore: Boolean): ParentIdQueryDefinition = copy(ignoreUnmapped = ignore.some)
}
