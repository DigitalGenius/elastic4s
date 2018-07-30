package com.sksamuel.elastic4s_6_2_11.indexes

import com.sksamuel.elastic4s_6_2_11.XContentFieldValueWriter
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}

object IndexContentBuilder {
  def apply(request: IndexDefinition): XContentBuilder =
    request.source match {
      case Some(json) => XContentFactory.parse(json)
      case None =>
        val source = XContentFactory.jsonBuilder()
        request.fields.foreach(XContentFieldValueWriter(source, _))
        source.endObject()
        source
    }
}
