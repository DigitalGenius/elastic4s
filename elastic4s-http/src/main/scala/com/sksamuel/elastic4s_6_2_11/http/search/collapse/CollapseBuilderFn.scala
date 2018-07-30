package com.sksamuel.elastic4s_6_2_11.http.search.collapse

import com.sksamuel.elastic4s_6_2_11.http.search.queries.nested.InnerHitQueryBodyFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.collapse.CollapseDefinition

object CollapseBuilderFn {

  def apply(collapse: CollapseDefinition): XContentBuilder = {
    val builder = XContentFactory.jsonBuilder()
    builder.field("field", collapse.field)
    collapse.maxConcurrentGroupSearches.foreach(max => builder.field("max_concurrent_group_searches", max))
    collapse.inner
      .map(InnerHitQueryBodyFn.apply)
      .foreach(x => builder.rawField("inner_hits", x.string))
    builder.endObject()
    builder
  }
}
