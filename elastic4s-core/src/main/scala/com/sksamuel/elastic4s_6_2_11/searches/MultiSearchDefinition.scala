package com.sksamuel.elastic4s_6_2_11.searches

import com.sksamuel.exts.OptionImplicits._

case class MultiSearchDefinition(searches: Iterable[SearchDefinition], maxConcurrentSearches: Option[Int] = None) {
  def maxConcurrentSearches(max: Int): MultiSearchDefinition = copy(maxConcurrentSearches = max.some)
}
