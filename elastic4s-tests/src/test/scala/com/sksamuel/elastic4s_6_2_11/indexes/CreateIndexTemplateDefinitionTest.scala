package com.sksamuel.elastic4s_6_2_11.indexes

import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}

import scala.util.Try

class CreateIndexTemplateDefinitionTest
  extends WordSpec
    with Matchers
    with BeforeAndAfter
    with DockerTests {

  before {
    Try {
      http.execute {
        deleteIndex("matchme.template")
      }.await
    }
  }

  "Create Index Template HTTP request" should {
    "create and use the template for an index" in {
      http.execute {
        createIndexTemplate("matchme", "matchme.*").mappings(
          mapping("sometype1").fields(
            keywordField("field1"),
            geopointField("field2"),
            keywordField("field3"),
            intField("field4")
          )
        )
      }.await

      http.execute {
        createIndex("matchme.template").shards(1).waitForActiveShards(1)
      }.await

      val resp = http.execute {
        getMapping("matchme.template")
      }.await.right.get.result

      resp.map(_.index) shouldBe Seq("matchme.template")
      resp.head.mappings.keySet shouldBe Set("sometype1")
      resp.head.mappings("sometype1").keySet shouldBe Set("field1", "field2", "field3", "field4")
    }
  }
}
