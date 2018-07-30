package com.sksamuel.elastic4s_6_2_11.testkit

import com.sksamuel.elastic4s_6_2_11.ElasticsearchClientUri
import com.sksamuel.elastic4s_6_2_11.http.HttpClient

trait DockerTests extends com.sksamuel.elastic4s_6_2_11.http.ElasticDsl {
  val http = HttpClient(ElasticsearchClientUri("http://localhost:9200"))
}
