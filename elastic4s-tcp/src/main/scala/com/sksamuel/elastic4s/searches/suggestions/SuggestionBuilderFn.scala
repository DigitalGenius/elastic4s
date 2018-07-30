package com.sksamuel.elastic4s_6_2_11.searches.suggestions

import com.sksamuel.elastic4s_6_2_11.searches.suggestion._
import org.elasticsearch.search.suggest.SuggestionBuilder

object SuggestionBuilderFn {
  def apply(sugg: SuggestionDefinition): SuggestionBuilder[_ <: SuggestionBuilder[_]] = sugg match {
    case comp: CompletionSuggestionDefinition => CompletionSuggestionBuilderFn(comp)
    case phrase: PhraseSuggestionDefinition   => PhraseSuggestionBuilderFn(phrase)
    case term: TermSuggestionDefinition       => TermSuggestionBuilderFn(term)
  }
}
