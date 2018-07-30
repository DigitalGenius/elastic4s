package com.sksamuel.elastic4s_6_2_11

case class FetchSourceContext(fetchSource: Boolean,
                              includes: Array[String] = Array.empty,
                              excludes: Array[String] = Array.empty)
