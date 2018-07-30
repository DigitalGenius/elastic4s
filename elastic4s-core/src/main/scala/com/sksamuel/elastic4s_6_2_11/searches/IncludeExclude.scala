package com.sksamuel.elastic4s_6_2_11.searches

case class IncludeExclude(include: Seq[String], exclude: Seq[String])

case class IncludePartition(partition: Int, numPartitions: Int)
