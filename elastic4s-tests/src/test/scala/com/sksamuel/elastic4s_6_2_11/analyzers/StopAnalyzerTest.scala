package com.sksamuel.elastic4s_6_2_11.analyzers

import org.scalatest.{Matchers, WordSpec}

class StopAnalyzerTest extends WordSpec with AnalyzerApi with Matchers {

  "StopAnalyzer builder" should {
    "set stopwords" in {
      stopAnalyzer("testy").stopwords("a", "b").json.string shouldBe """{"type":"stop","stopwords":["a","b"]}"""
    }
  }
}
