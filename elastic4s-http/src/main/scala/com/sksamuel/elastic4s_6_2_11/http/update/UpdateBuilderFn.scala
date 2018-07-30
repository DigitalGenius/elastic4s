package com.sksamuel.elastic4s_6_2_11.http.update

import com.sksamuel.elastic4s_6_2_11.FieldsMapper
import com.sksamuel.elastic4s_6_2_11.http.ScriptBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.update.UpdateDefinition

object UpdateBuilderFn {
  def apply(request: UpdateDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder()

    request.documentSource.foreach { doc =>
      builder.rawField("doc", doc)
    }

    request.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }

    if (request.documentFields.nonEmpty) {
      builder.startObject("doc")
      request.documentFields.foreach {
        case (name, value) =>
          builder.autofield(name, FieldsMapper.mapper(value))
      }
      builder.endObject()
    }

    request.upsertSource.foreach { upsert =>
      builder.rawField("upsert", upsert)
    }

    if (request.upsertFields.nonEmpty) {
      builder.startObject("upsert")
      request.upsertFields.foreach {
        case (name, value) =>
          builder.autofield(name, FieldsMapper.mapper(value))
      }
      builder.endObject()
    }

    request.docAsUpsert.foreach(_ => builder.field("doc_as_upsert", true))
    request.scriptedUpsert.foreach(_ => builder.field("scripted_upsert", true))
    request.detectNoop.foreach(_ => builder.field("detect_noop", true))

    builder.endObject()
    builder
  }
}
