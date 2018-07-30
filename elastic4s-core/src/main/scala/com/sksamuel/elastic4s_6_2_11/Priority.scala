package com.sksamuel.elastic4s_6_2_11

sealed trait Priority
object Priority {
  case object Immediate extends Priority
  case object Urgent    extends Priority
  case object High      extends Priority
  case object Normal    extends Priority
  case object Low       extends Priority
  case object Languid   extends Priority
}
