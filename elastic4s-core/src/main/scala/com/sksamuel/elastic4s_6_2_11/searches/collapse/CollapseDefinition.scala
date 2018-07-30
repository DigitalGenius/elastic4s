package com.sksamuel.elastic4s_6_2_11.searches.collapse

import com.sksamuel.elastic4s_6_2_11.searches.queries.InnerHitDefinition
import com.sksamuel.exts.OptionImplicits._

case class CollapseDefinition(field: String,
                              inner: Option[InnerHitDefinition] = None,
                              maxConcurrentGroupSearches: Option[Int] = None) {

  def inner(inner: InnerHitDefinition): CollapseDefinition     = copy(inner = inner.some)
  def maxConcurrentGroupSearches(max: Int): CollapseDefinition = copy(maxConcurrentGroupSearches = max.some)
}
