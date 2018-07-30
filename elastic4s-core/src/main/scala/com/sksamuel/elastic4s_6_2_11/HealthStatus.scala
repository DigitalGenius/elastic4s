package com.sksamuel.elastic4s_6_2_11

object HealthStatus {
  case object Green  extends HealthStatus
  case object Yellow extends HealthStatus
  case object Red    extends HealthStatus
}

sealed trait HealthStatus
