package com.sksamuel.elastic4s_6_2_11.http.index

import com.sksamuel.elastic4s_6_2_11.VersionType

object VersionTypeHttpString {
  def apply(versionType: VersionType): String = versionType match {
    case VersionType.External    => "external"
    case VersionType.Internal    => "internal"
    case VersionType.ExternalGte => "external_gte"
    case VersionType.Force       => "force"
  }
}
