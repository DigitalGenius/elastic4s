package com.sksamuel.elastic4s_6_2_11.get

trait HitField {
  def name: String
  def value: AnyRef
  def values: Seq[AnyRef]
  def isMetadataField: Boolean
}
