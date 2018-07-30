package com.sksamuel.elastic4s_6_2_11.script

import com.sksamuel.elastic4s_6_2_11.EnumConversions
import com.sksamuel.elastic4s_6_2_11.searches.sort._
import org.elasticsearch.search.sort.{SortBuilder, SortBuilders}

object SortBuilderFn {

  def apply[T <: SortBuilder[T]](sort: SortDefinition): SortBuilder[T] = sort match {
    case script: ScriptSortDefinition => ScriptSortBuilderFn(script).asInstanceOf[SortBuilder[T]]
    case ScoreSortDefinition(order) =>
      SortBuilders.scoreSort().order(EnumConversions.sortOrder(order)).asInstanceOf[SortBuilder[T]]
    case field: FieldSortDefinition     => FieldSortBuilderFn(field).asInstanceOf[SortBuilder[T]]
    case geo: GeoDistanceSortDefinition => GeoDistanceSortBuilderFn(geo).asInstanceOf[SortBuilder[T]]
  }
}
