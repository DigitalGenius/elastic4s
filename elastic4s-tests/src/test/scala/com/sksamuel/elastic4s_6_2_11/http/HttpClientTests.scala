package com.sksamuel.elastic4s_6_2_11.http

import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{FlatSpec, Matchers}

import scala.util.Try

class HttpClientTests extends FlatSpec with Matchers with DockerTests {

  Try {
    http.execute {
      deleteIndex("testindex")
    }.await
  }

  "DefaultHttpClient" should "support utf-8" in {
    http.execute {
      indexInto("testindex" / "testindex").doc("""{ "text":"¡Hola! ¿Qué tal?" }""")
    }.await.right.get.result.result shouldBe "created"
  }
}
