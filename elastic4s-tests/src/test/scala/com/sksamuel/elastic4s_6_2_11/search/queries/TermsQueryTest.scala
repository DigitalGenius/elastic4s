package com.sksamuel.elastic4s_6_2_11.search.queries

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{FlatSpec, Matchers}

class TermsQueryTest
  extends FlatSpec
    with DockerTests
    with Matchers {

  http.execute {
    createIndex("lords").mappings(
      mapping("people").fields(
        keywordField("name")
      )
    )
  }.await

  http.execute {
    createIndex("lordsfanclub").mappings(
      mapping("fans").fields(
        keywordField("lordswelike")
      )
    )
  }.await

  http.execute {
    bulk(
      indexInto("lords/people") fields ("name" -> "nelson"),
      indexInto("lords/people") fields ("name" -> "edmure"),
      indexInto("lords/people") fields ("name" -> "umber"),
      indexInto("lords/people") fields ("name" -> "byron"),
      indexInto("lordsfanclub/fans") fields ("lordswelike" -> List("nelson", "edmure")) id "lordsAppreciationFanClub"
    ).refresh(RefreshPolicy.Immediate)
  }.await

  "a terms query" should "find multiple terms using 'or'" in {

    val resp = http.execute {
      search("lords") query termsQuery("name", "nelson", "byron")
    }.await.right.get.result

    resp.hits.hits.map(_.sourceAsString).toSet shouldBe Set("""{"name":"nelson"}""", """{"name":"byron"}""")
  }

  it should "lookup terms to search from a document in another index" in {
    val resp = http.execute {
      search("lords") query termsQuery("name", List.empty[String])
        .ref("lordsfanclub", "fans", "lordsAppreciationFanClub")
        .path("lordswelike")
    }.await.right.get.result

    resp.hits.hits.map(_.sourceAsString).toSet shouldBe Set("""{"name":"nelson"}""", """{"name":"edmure"}""")
  }

  it should "return no results when an empty array is passed" in {
    val resp = http.execute {
      search("lords") query termsQuery("name", Seq.empty[String])
    }.await.right.get.result

    resp.hits.hits.map(_.sourceAsString).toSet shouldBe Set.empty[String]
  }

}
