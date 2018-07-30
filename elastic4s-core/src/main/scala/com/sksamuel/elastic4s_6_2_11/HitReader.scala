package com.sksamuel.elastic4s_6_2_11

trait HitReader[T] {
  def read(hit: Hit): Either[Throwable, T]
}

trait AggReader[T] {
  def read(json: String): Either[Throwable, T]
}
