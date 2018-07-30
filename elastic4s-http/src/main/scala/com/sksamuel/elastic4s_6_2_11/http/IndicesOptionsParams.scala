package com.sksamuel.elastic4s_6_2_11.http

import com.sksamuel.elastic4s_6_2_11.admin.IndicesOptions

object IndicesOptionsParams {
  def apply(opts: IndicesOptions): Map[String, String] = {

    val expand =
      if (opts.expandWildcardClosed && opts.expandWildcardsOpen) "all"
      else if (opts.expandWildcardsOpen) "open"
      else if (opts.expandWildcardClosed) "closed"
      else "none"

    Map(
      "ignore_unavailable" -> opts.ignoreUnavailable.toString,
      "allow_no_indices"   -> opts.allowNoIndices.toString,
      "expand_wildcards"   -> expand
    )
  }
}
