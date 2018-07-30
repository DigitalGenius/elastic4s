package com.sksamuel.elastic4s_6_2_11.http.update

import com.sksamuel.elastic4s_6_2_11.http.JsonSugar
import com.sksamuel.elastic4s_6_2_11.script.ScriptDefinition
import org.scalatest.WordSpec

class UpdateByQueryBodyFnTest extends WordSpec with JsonSugar {

  import com.sksamuel.elastic4s_6_2_11.http.ElasticDsl._

  "update by query" should {
    "generate correct body" when {
      "script is specified" in {
        val q = updateIn("test" / "type").query(matchQuery("field", 123)).script(ScriptDefinition("script", Some("painless")))
        UpdateByQueryBodyFn(q).string() should matchJson(
          """{"query":{"match":{"field":{"query":123}}},"script":{"lang":"painless","source":"script"}}"""
        )
      }
      "script is not specified" in {
        val q = updateIn("test" / "type").query(matchQuery("field", 123))
        UpdateByQueryBodyFn(q).string() should matchJson(
          """{"query":{"match":{"field":{"query":123}}}}"""
        )
      }
    }
  }
}
