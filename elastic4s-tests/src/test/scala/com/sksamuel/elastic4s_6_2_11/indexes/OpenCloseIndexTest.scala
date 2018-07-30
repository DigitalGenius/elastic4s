package com.sksamuel.elastic4s_6_2_11.indexes

import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class OpenCloseIndexTest extends WordSpec with Matchers with DockerTests {

  Try {
    http.execute {
      deleteIndex("pasta")
    }.await
  }

  http.execute {
    createIndex("pasta").mappings(
      mapping("types").fields(
        textField("name"),
        textField("region")
      )
    )
  }.await

  "close index" should {
    "acknowledge" in {
      http.execute {
        closeIndex("pasta")
      }.await.right.get.result.acknowledged shouldBe true
    }
  }

  "open index" should {
    "acknowledge" in {
      http.execute {
        openIndex("pasta")
      }.await.right.get.result.acknowledged shouldBe true
    }
  }
}
