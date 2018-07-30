package com.sksamuel.elastic4s_6_2_11.searches

import com.sksamuel.elastic4s_6_2_11.DocumentRef

case class TermsLookup(ref: DocumentRef, path: String, routing: Option[String] = None)
