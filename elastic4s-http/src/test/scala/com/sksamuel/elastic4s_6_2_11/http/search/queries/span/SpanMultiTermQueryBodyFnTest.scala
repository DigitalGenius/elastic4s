package com.sksamuel.elastic4s_6_2_11.http.search.queries.span

import com.sksamuel.elastic4s_6_2_11.searches.queries.PrefixQuery
import com.sksamuel.elastic4s_6_2_11.searches.queries.span.SpanMultiTermQueryDefinition
import org.scalatest.FunSuite

import scala.util.parsing.json.JSON

class SpanMultiTermQueryBodyFnTest extends FunSuite {

  test("SpanMultiTermQueryBodyFn apply should return appropriate XContentBuilder") {
    val builder = SpanMultiTermQueryBodyFn.apply(SpanMultiTermQueryDefinition(
      PrefixQuery("user", "ki"),
      boost = Some(2.0),
      queryName = Some("rootName")
    ))

    val actual = JSON.parseRaw(builder.string())
    val expected = JSON.parseRaw(
      """
        |{
        |    "span_multi":{
        |        "match":{
        |            "prefix" : { "user" :  { "value" : "ki" } }
        |        },
        |        "boost":2.0,
        |        "_name":"rootName"
        |    }
        |}""".stripMargin)

    assert(actual === expected)
  }
}
