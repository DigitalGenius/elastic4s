package com.sksamuel.elastic4s_6_2_11.analyzers

import org.scalatest.{Matchers, WordSpec}

class PatternAnalyzerTest extends WordSpec with AnalyzerApi with Matchers {

  "PatternAnalyzer builder" should {
    "set language" in {
      snowballAnalyzer("testy")
        .language("klingon")
        .json
        .string shouldBe """{"type":"snowball","language":"klingon"}"""
    }
    "set stopwords" in {
      snowballAnalyzer("testy")
        .stopwords("a", "b")
        .json
        .string shouldBe """{"type":"snowball","language":"English","stopwords":["a","b"]}"""
    }
    "not set stopwords if not specified" in {
      snowballAnalyzer("testy").json.string shouldBe """{"type":"snowball","language":"English"}"""
    }
  }
}
