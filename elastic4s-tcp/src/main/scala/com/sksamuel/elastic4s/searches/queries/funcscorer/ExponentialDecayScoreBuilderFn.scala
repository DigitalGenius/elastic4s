package com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer

import com.sksamuel.elastic4s_6_2_11.EnumConversions
import org.elasticsearch.index.query.functionscore.{ExponentialDecayFunctionBuilder, ScoreFunctionBuilders}

object ExponentialDecayScoreBuilderFn {
  def apply(exp: ExponentialDecayScoreDefinition): ExponentialDecayFunctionBuilder = {
    val builder = (exp.offset, exp.decay) match {
      case (Some(o), Some(d)) => ScoreFunctionBuilders.exponentialDecayFunction(exp.field, exp.origin, exp.scale, o, d)
      case (Some(o), None)    => ScoreFunctionBuilders.exponentialDecayFunction(exp.field, exp.origin, exp.scale, o)
      case (None, Some(d))    => ScoreFunctionBuilders.exponentialDecayFunction(exp.field, exp.origin, exp.scale, null, d)
      case _                  => ScoreFunctionBuilders.exponentialDecayFunction(exp.field, exp.origin, exp.scale)
    }
    exp.weight.map(_.toFloat).foreach(builder.setWeight)
    exp.multiValueMode.map(EnumConversions.multiValueMode).foreach(builder.setMultiValueMode)
    builder
  }
}
