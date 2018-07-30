package com.sksamuel.elastic4s_6_2_11

case class DocumentRef(index: String, `type`: String, id: String) {
  def indexAndType: IndexAndType = IndexAndType(index, `type`)
}
