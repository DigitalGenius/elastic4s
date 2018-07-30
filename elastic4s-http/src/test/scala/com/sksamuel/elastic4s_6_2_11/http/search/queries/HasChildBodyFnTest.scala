package com.sksamuel.elastic4s_6_2_11.http.search.queries

import com.sksamuel.elastic4s_6_2_11.http.search.queries.nested.HasChildBodyFn
import com.sksamuel.elastic4s_6_2_11.searches.ScoreMode
import com.sksamuel.elastic4s_6_2_11.searches.queries.{HasChildQueryDefinition, InnerHitDefinition}
import com.sksamuel.elastic4s_6_2_11.searches.queries.matches.MatchQueryDefinition
import org.scalatest.{FunSuite, Matchers}

class HasChildBodyFnTest extends FunSuite with Matchers {

  test("has child should generate expected json") {
    val q = HasChildQueryDefinition("blog_tag", MatchQueryDefinition("tag", "something"), ScoreMode.Min)
      .boost(1.2)
      .minMaxChildren(2, 10)
      .ignoreUnmapped(true)
      .queryName("myquery")
      .innerHit(InnerHitDefinition("inners"))
    HasChildBodyFn(q).string() shouldBe
      """{"has_child":{"type":"blog_tag","min_children":2,"max_children":10,"score_mode":"min","query":{"match":{"tag":{"query":"something"}}},"ignore_unmapped":true,"boost":1.2,"_name":"myquery","inner_hits":{"name":"inners"}}}"""
  }
}
