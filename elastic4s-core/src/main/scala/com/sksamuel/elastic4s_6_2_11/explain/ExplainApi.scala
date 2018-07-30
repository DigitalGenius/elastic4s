package com.sksamuel.elastic4s_6_2_11.explain

import com.sksamuel.elastic4s_6_2_11.DocumentRef

trait ExplainApi {
  def explain(ref: DocumentRef): ExplainDefinition                          = ExplainDefinition(ref.indexAndType, ref.id)
  def explain(index: String, `type`: String, id: String): ExplainDefinition = explain(DocumentRef(index, `type`, id))
}
