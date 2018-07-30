package com.sksamuel.elastic4s_6_2_11.searches.suggestions

import com.sksamuel.elastic4s_6_2_11.EnumConversions
import com.sksamuel.elastic4s_6_2_11.searches.suggestion.TermSuggestionDefinition
import org.elasticsearch.search.suggest.SuggestBuilders
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder

object TermSuggestionBuilderFn {

  def apply(sugg: TermSuggestionDefinition): TermSuggestionBuilder = {

    val builder = SuggestBuilders.termSuggestion(sugg.fieldname)

    sugg.analyzer.foreach(builder.analyzer)
    sugg.shardSize.foreach(builder.shardSize(_))
    sugg.size.foreach(builder.size)
    sugg.text.foreach(builder.text)

    sugg.accuracy.map(_.toFloat).foreach(builder.accuracy)
    sugg.maxEdits.foreach(builder.maxEdits)
    sugg.maxInspections.foreach(builder.maxInspections)
    sugg.maxTermFreq.map(_.toFloat).foreach(builder.maxTermFreq)
    sugg.minDocFreq.map(_.toFloat).foreach(builder.minDocFreq)
    sugg.minWordLength.foreach(builder.minWordLength)
    sugg.prefixLength.foreach(builder.prefixLength)
    sugg.sort.map(EnumConversions.sortBy).foreach(builder.sort)
    sugg.stringDistance.map(EnumConversions.stringDistance).foreach(builder.stringDistance)
    sugg.suggestMode.map(EnumConversions.suggestMode).foreach(builder.suggestMode)

    builder
  }
}
