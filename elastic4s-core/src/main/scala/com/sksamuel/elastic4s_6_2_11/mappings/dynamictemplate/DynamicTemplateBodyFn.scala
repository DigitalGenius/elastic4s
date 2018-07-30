package com.sksamuel.elastic4s_6_2_11.mappings.dynamictemplate

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.mappings.FieldBuilderFn

object DynamicTemplateBodyFn {

  def build(dyn: DynamicTemplateDefinition): XContentBuilder = {

    val builder = XContentFactory.obj()
    builder.startObject(dyn.name)

    dyn.`match`.foreach(builder.field("match", _))
    dyn.unmatch.foreach(builder.field("unmatch", _))
    dyn.pathMatch.foreach(builder.field("path_match", _))
    dyn.pathUnmatch.foreach(builder.field("path_unmatch", _))
    dyn.MatchPattern.foreach(builder.field("match_pattern", _))
    dyn.matchMappingType.foreach(builder.field("match_mapping_type", _))

    builder.rawField("mapping", FieldBuilderFn(dyn.mapping))

    builder.endObject()
  }
}
