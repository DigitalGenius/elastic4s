package com.sksamuel.elastic4s_6_2_11.http.cluster

import com.sksamuel.elastic4s_6_2_11.cluster.ClusterSettingsDefinition
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}

object ClusterBodyBuilderFn {
  def apply(request: ClusterSettingsDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    if(request.persistentSettings.nonEmpty) {
      builder.startObject("persistent")
      request.persistentSettings.foreach(t ⇒ builder.field(t._1, t._2))
      builder.endObject()
    }

    if(request.transientSettings.nonEmpty) {
      builder.startObject("transient")
      request.transientSettings.foreach(t ⇒ builder.field(t._1, t._2))
      builder.endObject()
    }
    builder
  }
}
