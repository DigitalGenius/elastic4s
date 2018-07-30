package com.sksamuel.elastic4s_6_2_11.searches

import scala.language.implicitConversions

case class Highlight(options: HighlightOptionsDefinition, fields: Iterable[HighlightFieldDefinition])

trait HighlightApi {
  def highlightOptions()       = HighlightOptionsDefinition()
  def highlight(field: String) = HighlightFieldDefinition(field)
}
