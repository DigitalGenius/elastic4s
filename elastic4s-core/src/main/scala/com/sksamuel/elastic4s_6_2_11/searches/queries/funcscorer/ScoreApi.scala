package com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer

import com.sksamuel.elastic4s_6_2_11.script.ScriptDefinition
import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition

trait ScoreApi {

  def randomScore(seed: Int) = RandomScoreFunctionDefinition(seed)

  def scriptScore(script: ScriptDefinition) = ScriptScoreDefinition(script)

  def gaussianScore(field: String, origin: String, scale: String) = GaussianDecayScoreDefinition(field, origin, scale)

  def linearScore(field: String, origin: String, scale: String) = LinearDecayScoreDefinition(field, origin, scale)

  def exponentialScore(field: String, origin: String, scale: String) =
    ExponentialDecayScoreDefinition(field, origin, scale)

  def fieldFactorScore(field: String) = FieldValueFactorDefinition(field)

  def weightScore(boost: Double) = WeightScoreDefinition(boost)
}
