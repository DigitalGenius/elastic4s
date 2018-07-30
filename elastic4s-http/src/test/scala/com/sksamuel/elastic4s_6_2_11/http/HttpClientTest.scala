package com.sksamuel.elastic4s_6_2_11.http

import com.sksamuel.elastic4s_6_2_11.ElasticsearchClientUri
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.duration.Duration

class HttpClientTest extends FlatSpec with Matchers with ElasticDsl {

  "HttpClient" should "throw an error when it cannot connect" in {
    intercept[JavaClientExceptionWrapper] {
      val client = HttpClient(ElasticsearchClientUri("123", 1))
      client.execute {
        indexInto("a-index" / "a-type") id "a-id" fields Map("wibble" -> "foo")
      }.await(Duration.Inf)
    }
  }
}
