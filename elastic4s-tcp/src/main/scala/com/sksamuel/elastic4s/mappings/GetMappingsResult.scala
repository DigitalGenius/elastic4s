package com.sksamuel.elastic4s_6_2_11.mappings

import com.sksamuel.elastic4s_6_2_11.IndexAndType
import org.elasticsearch.action.admin.indices.mapping.get.GetMappingsResponse
import org.elasticsearch.cluster.metadata.MappingMetaData

import scala.collection.JavaConverters._

case class GetMappingsResult(original: GetMappingsResponse) {

  def mappingsFor(index: String): Map[String, MappingMetaData] = mappings.getOrElse(index, Map.empty)

  def mappingFor(indexAndType: IndexAndType): MappingMetaData = mappings(indexAndType.index)(indexAndType.`type`)

  def propertiesFor(indexAndType: IndexAndType): Map[String, Any] =
    mappingFor(indexAndType).sourceAsMap().get("properties").asInstanceOf[java.util.Map[String, _]].asScala.toMap

  def fieldFor(indexAndType: IndexAndType, field: String): Map[String, Any] =
    propertiesFor(indexAndType).get("field").asInstanceOf[java.util.Map[String, _]].asScala.toMap

  // returns mappings of index name to a map of types to mapping data
  def mappings: Map[String, Map[String, MappingMetaData]] =
    original.mappings.iterator.asScala.map { x =>
      x.key -> x.value.iterator.asScala.map { y =>
        y.key -> y.value
      }.toMap
    }.toMap
}
