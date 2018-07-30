package com.sksamuel.elastic4s_6_2_11.count

import com.sksamuel.elastic4s_6_2_11.Indexes
import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition
import com.sksamuel.exts.OptionImplicits._

case class CountDefinition(indexes: Indexes, types: Seq[String], query: Option[QueryDefinition] = None) {
  def filter(query: QueryDefinition): CountDefinition = copy(query = query.some)
}
