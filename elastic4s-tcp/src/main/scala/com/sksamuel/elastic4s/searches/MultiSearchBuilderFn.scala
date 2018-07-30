package com.sksamuel.elastic4s_6_2_11.searches

import org.elasticsearch.action.search.MultiSearchRequestBuilder
import org.elasticsearch.client.Client

object MultiSearchBuilderFn {
  def apply(client: Client, multi: MultiSearchDefinition): MultiSearchRequestBuilder = {
    val builder = client.prepareMultiSearch()
    multi.searches.toSeq.map(SearchBuilderFn.apply(client, _)).foreach(builder.add)
    builder
  }
}
