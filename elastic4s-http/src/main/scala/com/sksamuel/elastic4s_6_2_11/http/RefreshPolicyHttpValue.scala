package com.sksamuel.elastic4s_6_2_11.http

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy

object RefreshPolicyHttpValue {
  def apply(policy: RefreshPolicy): String = policy match {
    case RefreshPolicy.Immediate => "true"
    case RefreshPolicy.WaitFor   => "wait_for"
    case RefreshPolicy.None      => "false"
  }
}
