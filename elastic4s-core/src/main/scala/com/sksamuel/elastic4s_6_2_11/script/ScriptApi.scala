package com.sksamuel.elastic4s_6_2_11.script

import scala.language.implicitConversions

trait ScriptApi {
  def script(script: String): ScriptDefinition = ScriptDefinition(script)
  def script(name: String, script: String)     = ScriptDefinition(script)
}
