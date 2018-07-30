package com.sksamuel.elastic4s_6_2_11.script

import com.sksamuel.elastic4s_6_2_11.EnumConversions
import com.sksamuel.elastic4s_6_2_11.searches.QueryBuilderFn
import com.sksamuel.elastic4s_6_2_11.searches.sort.FieldSortDefinition
import org.elasticsearch.search.sort.{FieldSortBuilder, SortBuilders}

object FieldSortBuilderFn {

  def apply(d: FieldSortDefinition): FieldSortBuilder = {
    val builder = SortBuilders.fieldSort(d.field)
    d.nestedFilter.map(QueryBuilderFn.apply).foreach(builder.setNestedFilter)
    d.unmappedType.foreach(builder.unmappedType)
    d.missing.foreach(builder.missing)
    builder.order(EnumConversions.sortOrder(d.order))
    d.nestedPath.foreach(builder.setNestedPath)
    d.sortMode.map(EnumConversions.sortMode).foreach(builder.sortMode)
    builder
  }
}
