package com.sksamuel.elastic4s_6_2_11.http.search.queries.term

import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.queries.term.{TermsLookupQueryDefinition, TermsSetQuery}

object TermsLookupQueryBodyFn {
  def apply(t: TermsLookupQueryDefinition): XContentBuilder = {

    val builder = XContentFactory.jsonBuilder().startObject("terms")

    builder.startObject(t.field)
    builder.field("index", t.termsLookup.ref.index)
    builder.field("type", t.termsLookup.ref.`type`)
    builder.field("id", t.termsLookup.ref.id)

    builder.field("path", t.termsLookup.path)
    t.termsLookup.routing.foreach(builder.field("routing", _))
    builder.endObject()

    t.queryName.foreach(builder.field("_name", _))

    builder.endObject().endObject()
  }
}

object TermsSetQueryBodyFn {
  def apply(t: TermsSetQuery): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder().startObject("terms_set")
    builder.startObject(t.field)
    builder.array("terms", t.terms.map(_.toString).toArray[String])
    t.minimumShouldMatchField.foreach(builder.field("minimum_should_match_field", _))
    builder.endObject().endObject()
  }
}
