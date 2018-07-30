package com.sksamuel.elastic4s_6_2_11.search

import com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer.{FunctionScoreBuilderFn, MultiValueMode}
import com.sksamuel.elastic4s_6_2_11.{ElasticDsl, JsonSugar}
import org.elasticsearch.common.xcontent.{ToXContent, XContentFactory}
import org.scalatest.FlatSpec

class FunctionScoreDslTest extends FlatSpec with JsonSugar with ElasticDsl {
  it should "generate correct json for a function score query with gaussian decay function scorer" in {
    val req = functionScoreQuery(matchAllQuery()) maxBoost 1.0 functions gaussianScore("myfield", "1 2", "3km").offset("1km").decay(0.2).weight(0.2).multiValueMode(MultiValueMode.Min).filter(matchAllQuery())
    val builder = FunctionScoreBuilderFn(req)
    val actual = builder.toXContent(XContentFactory.jsonBuilder(), ToXContent.EMPTY_PARAMS).string()
    actual should matchJsonResource("/json/score/function_score_gaussian.json")
  }
}
