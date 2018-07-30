package com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer

import org.elasticsearch.index.query.functionscore.{RandomScoreFunctionBuilder, ScoreFunctionBuilders}

object RandomScoreFunctionBuilderFn {
  def apply(random: RandomScoreFunctionDefinition): RandomScoreFunctionBuilder = {
    val builder = ScoreFunctionBuilders.randomFunction().seed(random.seed)
    random.weight.map(_.toFloat).foreach(builder.setWeight)
    builder
  }
}
