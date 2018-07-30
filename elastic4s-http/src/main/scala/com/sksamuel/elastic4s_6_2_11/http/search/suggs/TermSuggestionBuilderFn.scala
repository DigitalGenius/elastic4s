package com.sksamuel.elastic4s_6_2_11.http.search.suggs

import com.sksamuel.elastic4s_6_2_11.http.EnumConversions
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.suggestion.TermSuggestionDefinition

object TermSuggestionBuilderFn {
  def apply(term: TermSuggestionDefinition): XContentBuilder = {

    val builder = XContentFactory.obj()

    term.text.foreach(builder.field("text", _))

    builder.startObject("term")
    builder.field("field", term.fieldname)
    term.analyzer.foreach(builder.field("analyzer", _))
    term.lowercaseTerms.foreach(builder.field("lowercase_terms", _))
    term.maxEdits.foreach(builder.field("max_edits", _))
    term.minWordLength.foreach(builder.field("min_word_length", _))
    term.maxInspections.foreach(builder.field("max_inspections", _))
    term.minDocFreq.foreach(builder.field("min_doc_freq", _))
    term.maxTermFreq.foreach(builder.field("max_term_freq", _))
    term.prefixLength.foreach(builder.field("prefix_length", _))
    term.size.foreach(builder.field("size", _))
    term.shardSize.foreach(builder.field("shard_size", _))
    term.sort.map(EnumConversions.sortBy).foreach(builder.field("sort", _))
    term.stringDistance.map(EnumConversions.stringDistance).foreach(builder.field("string_distance", _))
    term.suggestMode.map(EnumConversions.suggestMode).foreach(builder.field("suggest_mode", _))

    builder
  }
}
