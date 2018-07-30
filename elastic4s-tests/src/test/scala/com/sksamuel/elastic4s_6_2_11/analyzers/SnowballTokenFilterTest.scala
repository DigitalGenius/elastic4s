package com.sksamuel.elastic4s_6_2_11.analyzers

import org.scalatest.{Matchers, WordSpec}

class SnowballTokenFilterTest extends WordSpec with TokenFilterDsl with Matchers {

  "SnowballTokenFilter builder" should {
    "set language" in {
      snowballTokenFilter("testy", "vulcan").json.string shouldBe """{"type":"snowball","language":"vulcan"}"""
    }
  }
}
