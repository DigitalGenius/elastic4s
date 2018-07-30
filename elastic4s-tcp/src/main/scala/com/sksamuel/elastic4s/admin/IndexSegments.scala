package com.sksamuel.elastic4s_6_2_11.admin

case class IndexSegments(original: org.elasticsearch.action.admin.indices.segments.IndexSegments) {

  import scala.collection.JavaConverters._

  def index: String = original.getIndex

  def shards: Map[Integer, IndexShardSegments] =
    Option(original.getShards)
      .map(_.asScala.map { case (k, v) => k -> IndexShardSegments(v) }.toMap)
      .getOrElse(Map.empty)
}
