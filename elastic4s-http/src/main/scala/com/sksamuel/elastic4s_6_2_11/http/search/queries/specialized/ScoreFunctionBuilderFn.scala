package com.sksamuel.elastic4s_6_2_11.http.search.queries.specialized

import com.sksamuel.elastic4s_6_2_11.json.XContentBuilder
import com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer._

object ScoreFunctionBuilderFn {
  def apply(func: ScoreFunctionDefinition): XContentBuilder =
    func match {
      case r: RandomScoreFunctionDefinition   => RandomScoreFunctionBuilderFn(r)
      case g: GaussianDecayScoreDefinition    => GaussianDecayScoreBuilderFn(g)
      case s: ScriptScoreDefinition           => ScriptScoreBuilderFn(s)
      case f: FieldValueFactorDefinition      => FieldValueFactorBuilderFn(f)
      case e: ExponentialDecayScoreDefinition => ExponentialDecayScoreBuilderFn(e)
      case w: WeightScoreDefinition           => WeightBuilderFn(w)
      case l: LinearDecayScoreDefinition      => LinearDecayScoreBuilderFn(l)
    }
}
