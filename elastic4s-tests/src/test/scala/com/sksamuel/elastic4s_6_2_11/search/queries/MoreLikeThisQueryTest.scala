package com.sksamuel.elastic4s_6_2_11.search.queries

import com.sksamuel.elastic4s_6_2_11.analyzers.StandardAnalyzer
import com.sksamuel.elastic4s_6_2_11.searches.queries.{ArtificialDocument, MoreLikeThisItem}
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import com.sksamuel.elastic4s_6_2_11.{DocumentRef, ElasticDsl, RefreshPolicy}
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class MoreLikeThisQueryTest extends WordSpec with Matchers with DockerTests {

  Try {
    http.execute(
      ElasticDsl.deleteIndex("drinks")
    ).await
  }

  http.execute {
    createIndex("drinks").mappings (
      mapping("drink") as (
        textField("text") store true analyzer StandardAnalyzer
        ) parent "a"
    ) shards 3
  }.await

  http.execute {
    bulk(
      indexInto("drinks/drink") fields ("text" -> "coors light is a coors beer by molson") id "4" parent "1",
      indexInto("drinks/drink") fields ("text" -> "Anheuser-Busch brews a cider called Strongbow") id "6" parent "1",
      indexInto("drinks/drink") fields ("text" -> "Gordons popular gin UK") id "7" parent "1",
      indexInto("drinks/drink") fields ("text" -> "coors regular is another coors beer by molson") id "8" parent "1",
      indexInto("drinks/drink") fields ("text" -> "Hendricks upmarket gin UK") id "9" parent "1"
    ).refresh(RefreshPolicy.Immediate)
  }.await

  "a more like this query" should {

    "find matches based on input text" in {
      val resp = http.execute {
        search("drinks") query {
          moreLikeThisQuery("text")
            .likeTexts("coors") minTermFreq 1 minDocFreq 1
        }
      }.await.right.get.result
      resp.hits.hits.map(_.id).toSet shouldBe Set("4", "8")
    }

    "find matches based on doc refs" in {
      val ref = DocumentRef("drinks", "drink", "4")
      val resp1 = http.execute {
        search("drinks").query {
          moreLikeThisQuery("text")
            .likeItems(MoreLikeThisItem(ref, Some("2"))) minTermFreq 1 minDocFreq 1
        }
      }.await.right.get.result
      resp1.hits.hits.map(_.id).toSet shouldBe Set()

      val resp2 = http.execute {
        search("drinks").query {
          moreLikeThisQuery("text")
            .likeItems(MoreLikeThisItem(ref, Some("1"))) minTermFreq 1 minDocFreq 1
        }
      }.await.right.get.result
      resp2.hits.hits.map(_.id).toSet shouldBe Set("8")
    }

    "support artifical docs" in {
      val resp = http.execute {
        search("drinks").query {
          moreLikeThisQuery("text")
            .artificialDocs(
              ArtificialDocument("drinks", "drink", """{ "text" : "gin" }""", Some("1"))
            ) minTermFreq 1 minDocFreq 1
        }
      }.await.right.get.result
      resp.hits.hits.map(_.id).toSet shouldBe Set("7", "9")
    }
  }
}
