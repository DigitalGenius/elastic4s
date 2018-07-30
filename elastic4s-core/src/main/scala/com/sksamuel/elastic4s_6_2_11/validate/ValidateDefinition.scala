package com.sksamuel.elastic4s_6_2_11.validate

import com.sksamuel.elastic4s_6_2_11.IndexesAndTypes
import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition
import com.sksamuel.exts.OptionImplicits._

case class ValidateDefinition(indexesAndTypes: IndexesAndTypes,
                              query: QueryDefinition,
                              rewrite: Option[Boolean] = None,
                              explain: Option[Boolean] = None) {
  require(indexesAndTypes != null, "value must not be null or empty")

  def rewrite(rewrite: Boolean): ValidateDefinition = copy(rewrite = rewrite.some)
  def explain(explain: Boolean): ValidateDefinition = copy(explain = explain.some)
}
