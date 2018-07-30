package com.sksamuel.elastic4s_6_2_11.delete

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class DeleteByIdTest extends WordSpec with Matchers with DockerTests {

  Try {
    http.execute {
      deleteIndex("lecarre")
    }.await
  }

  http.execute {
    createIndex("lecarre").mappings(
      mapping("characters").fields(
        textField("name")
      )
    ).shards(1).waitForActiveShards(1)
  }.await

  "delete by id request" should {
    "delete matched docs" in {

      http.execute {
        indexInto("lecarre" / "characters").fields("name" -> "jonathon pine").id("2").refresh(RefreshPolicy.Immediate)
      }.await

      http.execute {
        indexInto("lecarre" / "characters").fields("name" -> "george smiley").id("4").refresh(RefreshPolicy.Immediate)
      }.await

      http.execute {
        search("lecarre" / "characters").matchAllQuery()
      }.await.right.get.result.totalHits shouldBe 2

      http.execute {
        delete("2").from("lecarre" / "characters").refresh(RefreshPolicy.Immediate)
      }.await

      http.execute {
        search("lecarre" / "characters").matchAllQuery()
      }.await.right.get.result.totalHits shouldBe 1

      http.execute {
        delete("4").from("lecarre" / "characters").refresh(RefreshPolicy.Immediate)
      }.await

      http.execute {
        search("lecarre" / "characters").matchAllQuery()
      }.await.right.get.result.totalHits shouldBe 0
    }
  }
}
