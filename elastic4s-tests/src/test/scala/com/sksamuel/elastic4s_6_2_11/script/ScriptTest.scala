package com.sksamuel.elastic4s_6_2_11.script

import com.sksamuel.elastic4s_6_2_11.testkit.{DockerTests, ElasticMatchers}
import org.scalatest.FreeSpec

import scala.util.Try

class ScriptTest extends FreeSpec with ElasticMatchers with DockerTests {

  Try {
    http.execute {
      deleteIndex("script")
    }.await
  }

  http.execute {
    createIndex("script").mappings(
      mapping("tubestops").fields(
        textField("name").fielddata(true),
        textField("line").fielddata(true)
      )
    )
  }.await

  http.execute {
    bulk(
      indexInto("script/tubestops") fields("name" -> "south kensington", "line" -> "district"),
      indexInto("script/tubestops") fields("name" -> "earls court", "line" -> "district", "zone" -> 2),
      indexInto("script/tubestops") fields("name" -> "cockfosters", "line" -> "picadilly"),
      indexInto("script/tubestops") fields("name" -> "bank", "line" -> "northern")
    ).refreshImmediately
  }.await

  "script fields" - {
    "can access doc fields" in {
      val result = http.execute {
        search("script").matchQuery("name", "cockfosters").scriptfields(
          scriptField("a", "doc['line'].value")
        )
      }.await.right.get.result
      result.hits.hits.head.storedField("a").value shouldBe "picadilly"
    }
    "can use params" in {
      val result = http.execute {
        search("script") query "earls" scriptfields (
          scriptField("a") script (
            script("doc['zone'].value * params.fare") params Map("fare" -> 4.50)
            )
          )
      }.await.right.get.result
      result.hits.hits.head.storedField("a").value shouldBe 9.0
    }
  }
}
