package com.sksamuel.elastic4s_6_2_11.searches.queries.geo

import com.sksamuel.elastic4s_6_2_11.DistanceUnit
import com.sksamuel.elastic4s_6_2_11.searches.GeoPoint

object Shapes {

  case class Polygon(points: Seq[GeoPoint], holes: Option[Seq[GeoPoint]])

  case class Circle(point: GeoPoint, distance: (Double, DistanceUnit))
}
