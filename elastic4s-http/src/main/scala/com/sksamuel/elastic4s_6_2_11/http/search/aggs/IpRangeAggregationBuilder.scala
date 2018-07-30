package com.sksamuel.elastic4s_6_2_11.http.search.aggs

import com.sksamuel.elastic4s_6_2_11.http.ScriptBuilderFn
import com.sksamuel.elastic4s_6_2_11.json.{XContentBuilder, XContentFactory}
import com.sksamuel.elastic4s_6_2_11.searches.aggs.IpRangeAggregationDefinition

object IpRangeAggregationBuilder {
  def apply(agg: IpRangeAggregationDefinition): XContentBuilder = {

    val builder = XContentFactory.obj().startObject("ip_range")

    agg.field.foreach(builder.field("field", _))
    agg.format.foreach(builder.field("format", _))
    agg.missing.foreach(builder.autofield("missing", _))
    agg.keyed.foreach(builder.field("keyed", _))
    agg.script.foreach { script =>
      builder.rawField("script", ScriptBuilderFn(script))
    }

    builder.startArray("ranges")
    agg.unboundedTo.foreach {
      case (keyOpt, to) =>
        builder.startObject()
        keyOpt.foreach(builder.field("key", _))
        builder.field("to", to)
        builder.endObject()
    }
    agg.ranges.foreach {
      case (keyOpt, from, to) =>
        builder.startObject()
        keyOpt.foreach(builder.field("key", _))
        builder.field("from", from)
        builder.field("to", to)
        builder.endObject()
    }
    agg.unboundedFrom.foreach {
      case (keyOpt, from) =>
        builder.startObject()
        keyOpt.foreach(builder.field("key", _))
        builder.field("from", from)
        builder.endObject()
    }
    agg.maskRanges.foreach {
      case (keyOpt, mask) =>
        builder.startObject()
        keyOpt.foreach(builder.field("key", _))
        builder.field("mask", mask)
        builder.endObject()
    }
    builder.endArray()

    builder.endObject()

    SubAggsBuilderFn(agg, builder)
    AggMetaDataFn(agg, builder)

    builder
  }
}
