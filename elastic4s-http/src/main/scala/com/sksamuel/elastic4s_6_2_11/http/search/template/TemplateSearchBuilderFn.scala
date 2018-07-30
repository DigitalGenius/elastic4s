package com.sksamuel.elastic4s_6_2_11.http.search.template

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.TemplateSearchDefinition

object TemplateSearchBuilderFn {
  def apply(req: TemplateSearchDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.field("id", req.name)
    if (req.params.nonEmpty) {
      builder.startObject("params")
      req.params.foreach { case (key, value) => builder.autofield(key, value) }
      builder.endObject()
    }
    builder.endObject()
  }
}
