package com.sksamuel.elastic4s_6_2_11.searches.sort

import com.sksamuel.elastic4s_6_2_11.DistanceUnit
import com.sksamuel.elastic4s_6_2_11.searches.GeoPoint
import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition
import com.sksamuel.elastic4s_6_2_11.searches.queries.geo.{GeoDistance, GeoValidationMethod}
import com.sksamuel.exts.OptionImplicits._

case class GeoDistanceSortDefinition(field: String,
                                     geohashes: Seq[String] = Nil,
                                     points: Seq[GeoPoint] = Nil,
                                     nestedFilter: Option[QueryDefinition] = None,
                                     nestedPath: Option[String] = None,
                                     sortMode: Option[SortMode] = None,
                                     order: Option[SortOrder] = None,
                                     unit: Option[DistanceUnit] = None,
                                     validation: Option[GeoValidationMethod] = None,
                                     geoDistance: Option[GeoDistance] = None)
    extends SortDefinition {

  def mode(mode: String): GeoDistanceSortDefinition   = sortMode(SortMode.valueOf(mode.toUpperCase))
  def mode(mode: SortMode): GeoDistanceSortDefinition = copy(sortMode = mode.some)

  def sortMode(mode: String): GeoDistanceSortDefinition   = sortMode(SortMode.valueOf(mode.toUpperCase))
  def sortMode(mode: SortMode): GeoDistanceSortDefinition = copy(sortMode = mode.some)

  def nestedPath(path: String): GeoDistanceSortDefinition             = copy(nestedPath = path.some)
  def nestedFilter(query: QueryDefinition): GeoDistanceSortDefinition = copy(nestedFilter = query.some)

  def order(order: SortOrder): GeoDistanceSortDefinition     = copy(order = order.some)
  def sortOrder(order: SortOrder): GeoDistanceSortDefinition = copy(order = order.some)

  def validation(validation: GeoValidationMethod): GeoDistanceSortDefinition = copy(validation = validation.some)

  def unit(unit: DistanceUnit): GeoDistanceSortDefinition           = copy(unit = unit.some)
  def geoDistance(distance: GeoDistance): GeoDistanceSortDefinition = copy(geoDistance = distance.some)
}
