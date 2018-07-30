package com.sksamuel.elastic4s_6_2_11.locks

trait LocksApi {
  def acquireGlobalLock() = AcquireGlobalLock()
  def releaseGlobalLock() = ReleaseGlobalLock()
}

case class AcquireGlobalLock()
case class ReleaseGlobalLock()
