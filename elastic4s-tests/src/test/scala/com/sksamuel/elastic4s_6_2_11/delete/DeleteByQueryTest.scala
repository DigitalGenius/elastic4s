package com.sksamuel.elastic4s_6_2_11.delete

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class DeleteByQueryTest extends WordSpec with Matchers with DockerTests {

  private val indexname = "charles_dickens"

  Try {
    http.execute {
      deleteIndex(indexname)
    }.await
  }

  http.execute {
    createIndex(indexname).mappings(
      mapping(indexname).fields(
        textField("name")
      )
    ).shards(1).waitForActiveShards(1)
  }.await

  "delete by query" should {
    "delete matched docs" in {
      http.execute {
        bulk(
          indexInto(indexname / indexname).fields("name" -> "mr bumbles").id("1"),
          indexInto(indexname / indexname).fields("name" -> "artful dodger").id("2"),
          indexInto(indexname / indexname).fields("name" -> "mrs bumbles").id("3"),
          indexInto(indexname / indexname).fields("name" -> "fagan").id("4")
        ).refresh(RefreshPolicy.Immediate)
      }.await

      http.execute {
        search(indexname).matchAllQuery()
      }.await.right.get.result.totalHits shouldBe 4

      http.execute {
        deleteByQuery(indexname, indexname, matchQuery("name", "bumbles")).refresh(RefreshPolicy.Immediate)
      }.await.right.get.result.deleted shouldBe 2

      http.execute {
        search(indexname).matchAllQuery()
      }.await.right.get.result.totalHits shouldBe 2
    }
    "return a Left[RequestFailure] when the delete fails" in {
      http.execute {
        deleteByQuery(",", indexname, matchQuery("name", "bumbles"))
      }.await.left.get.error.`type` shouldBe "action_request_validation_exception"
    }
  }
}
