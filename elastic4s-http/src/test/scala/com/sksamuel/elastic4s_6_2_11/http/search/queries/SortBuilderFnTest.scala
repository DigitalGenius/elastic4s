package com.sksamuel.elastic4s_6_2_11.http.search.queries

import com.sksamuel.elastic4s_6_2_11.DistanceUnit
import com.sksamuel.elastic4s_6_2_11.http.ElasticDsl._
import com.sksamuel.elastic4s_6_2_11.http.search.SearchBodyBuilderFn
import com.sksamuel.elastic4s_6_2_11.script.ScriptType
import com.sksamuel.elastic4s_6_2_11.searches.GeoPoint
import com.sksamuel.elastic4s_6_2_11.searches.queries.geo.GeoDistanceQueryDefinition
import com.sksamuel.elastic4s_6_2_11.searches.sort.{GeoDistanceSortDefinition, SortOrder, ScriptSortType}
import org.scalatest.{FunSuite, Matchers}
import com.sksamuel.exts.OptionImplicits._

class SortBuilderFnTest extends FunSuite with Matchers {

  test("sort script parameters are encoded with the correct type") {
    val scr = script("dummy script")
      .lang("painless")
      .scriptType(ScriptType.Source)
      .params(Map(
        "nump" -> 10.2,
        "stringp" -> "ciao",
        "boolp" -> true
      ))
    val request = scriptSort(scr)
      .typed(ScriptSortType.Number)
      .order(SortOrder.Desc)
    SortBuilderFn(request).string() shouldBe
      """{"_script":{"script":{"source":"dummy script","lang":"painless","params":{"nump":10.2,"stringp":"ciao","boolp":true}},"type":"number","order":"desc"}}"""
  }
}

