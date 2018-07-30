package com.sksamuel.elastic4s_6_2_11.searches.queries.term

import com.sksamuel.elastic4s_6_2_11.script.ScriptDefinition
import com.sksamuel.elastic4s_6_2_11.searches.TermsLookup
import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition
import com.sksamuel.exts.OptionImplicits._

case class TermsLookupQueryDefinition(field: String, termsLookup: TermsLookup, queryName: Option[String] = None)
    extends QueryDefinition {
  def queryName(name: String): TermsLookupQueryDefinition = copy(queryName = name.some)
}

case class TermsSetQuery(field: String,
                         terms: Set[Any],
                         minimumShouldMatchField: Option[Int] = None,
                         minimumShouldMatchScript: Option[ScriptDefinition] = None,
                         queryName: Option[String] = None) {
  require(terms.nonEmpty)
  def queryName(name: String): TermsSetQuery                            = copy(queryName = name.some)
  def minimumShouldMatchField(field: Int): TermsSetQuery                = copy(minimumShouldMatchField = field.some)
  def minimumShouldMatchScript(script: ScriptDefinition): TermsSetQuery = copy(minimumShouldMatchScript = script.some)

}
