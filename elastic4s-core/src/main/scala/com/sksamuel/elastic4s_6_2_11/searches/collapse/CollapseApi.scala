package com.sksamuel.elastic4s_6_2_11.searches.collapse

trait CollapseApi {
  def collapseField(field: String): CollapseDefinition = CollapseDefinition(field)
}
