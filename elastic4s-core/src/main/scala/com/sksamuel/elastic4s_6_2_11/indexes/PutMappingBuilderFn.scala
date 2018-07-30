package com.sksamuel.elastic4s_6_2_11.indexes

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.mappings.{MappingBuilderFn, PutMappingDefinition}

object PutMappingBuilderFn {

  def apply(pm: PutMappingDefinition): XContentBuilder =
    pm.rawSource match {
      case None         => MappingBuilderFn.build(pm)
      case Some(source) => XContentFactory.parse(source)
    }
}
