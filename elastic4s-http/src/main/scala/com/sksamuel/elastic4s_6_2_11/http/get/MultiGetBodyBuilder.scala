package com.sksamuel.elastic4s_6_2_11.http.get

import com.sksamuel.elastic4s_6_2_11.get.MultiGetDefinition
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}

object MultiGetBodyBuilder {
  def apply(request: MultiGetDefinition): XContentBuilder = {
    val builder = XContentFactory.obj()
    builder.startArray("docs")
    request.gets.foreach { get =>
      builder.startObject()
      builder.field("_index", get.indexAndType.index)
      builder.field("_type", get.indexAndType.`type`)
      builder.field("_id", get.id)
      get.fetchSource.foreach { context =>
        if (context.includes.nonEmpty || context.excludes.nonEmpty) {
          builder.startObject("_source")
          if (context.includes.nonEmpty)
            builder.array("include", context.includes)
          if (context.excludes.nonEmpty)
            builder.array("exclude", context.excludes)
          builder.endObject()
        } else {
          builder.field("_source", false)
        }
      }
      if (get.storedFields.nonEmpty) {
        builder.array("stored_fields", get.storedFields.toArray)
      }
      builder.endObject()
    }
    builder.endArray()
    builder.endObject()
  }
}
