package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.elastic4s_6_2_11.EnumConversions
import com.sksamuel.elastic4s_6_2_11.searches.queries.geo.GeoPolygonQueryDefinition
import org.elasticsearch.index.query.{GeoPolygonQueryBuilder, QueryBuilders}

import scala.collection.JavaConverters._

object GeoPolygonQueryBuilderFn {

  def apply(q: GeoPolygonQueryDefinition): GeoPolygonQueryBuilder = {
    val builder = QueryBuilders.geoPolygonQuery(q.field, q.points.map(EnumConversions.geo).asJava)
    q.boost.map(_.toFloat).foreach(builder.boost)
    q.queryName.foreach(builder.queryName)
    q.validationMethod.map(EnumConversions.geoValidationMethod).foreach(builder.setValidationMethod)
    q.ignoreUnmapped.foreach(builder.ignoreUnmapped)
    builder
  }
}
