package com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer

import com.sksamuel.elastic4s_6_2_11.EnumConversions
import org.elasticsearch.index.query.functionscore.{FieldValueFactorFunctionBuilder, ScoreFunctionBuilders}

object FieldValueFactorBuilderFn {
  def apply(f: FieldValueFactorDefinition): FieldValueFactorFunctionBuilder = {
    val builder = ScoreFunctionBuilders.fieldValueFactorFunction(f.fieldName)
    f.factor.map(_.toFloat).foreach(builder.factor)
    f.missing.foreach(builder.missing)
    f.modifier.map(EnumConversions.modifier).foreach(builder.modifier)
    builder
  }
}
