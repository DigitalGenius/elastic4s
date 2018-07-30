package com.sksamuel.elastic4s_6_2_11.http

import com.sksamuel.elastic4s_6_2_11.script.{ScriptDefinition, ScriptType}
import org.scalatest.{FunSuite, Matchers}

class ScriptBuilderFnTest extends FunSuite with Matchers {

  test("should handle recursive maps") {
    ScriptBuilderFn(ScriptDefinition("myscript", params = Map("a" -> 1.2, "b" -> Map("c" -> true, "d" -> List(Map("e" -> 3)))))).string shouldBe
      """{"source":"myscript","params":{"a":1.2,"b":{"c":true,"d":[{"e":3}]}}}"""
  }

  test("should handle lists of maps") {
    ScriptBuilderFn(ScriptDefinition("myscript", params = Map("a" -> 1.2, "b" -> Map("c" -> true, "d" -> List(Map("e" -> 3)))))).string shouldBe
      """{"source":"myscript","params":{"a":1.2,"b":{"c":true,"d":[{"e":3}]}}}"""
  }

  test("should handle recursive lists") {
    ScriptBuilderFn(ScriptDefinition("myscript", params = Map("a" -> List(List(List("foo")))))).string shouldBe
      """{"source":"myscript","params":{"a":[[["foo"]]]}}"""
  }

  test("should handle maps of lists") {
    ScriptBuilderFn(ScriptDefinition("myscript", params = Map("a" -> List(3, 2, 1)))).string shouldBe
      """{"source":"myscript","params":{"a":[3,2,1]}}"""
  }

  test("should handle mixed lists") {
    ScriptBuilderFn(ScriptDefinition("myscript", params = Map("a" -> List(List(true, 1.2, List("foo"), Map("w" -> "wibble")))))).string shouldBe
      """{"source":"myscript","params":{"a":[[true,1.2,["foo"],{"w":"wibble"}]]}}"""
  }

  test("should handle stored scripts") {
    ScriptBuilderFn(ScriptDefinition("convert_currency", scriptType = ScriptType.Stored, params = Map("field" -> "price", "conversion_rate" -> 0.835526591))).string shouldBe
      """{"id":"convert_currency","params":{"field":"price","conversion_rate":0.835526591}}"""
  }
}