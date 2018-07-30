package com.sksamuel.elastic4s_6_2_11.searches.queries

import com.sksamuel.elastic4s_6_2_11.DocumentRef

case class PercolateQueryDefinition(field: String,
                                    `type`: String,
                                    ref: Option[DocumentRef] = None,
                                    source: Option[String] = None)
    extends QueryDefinition
