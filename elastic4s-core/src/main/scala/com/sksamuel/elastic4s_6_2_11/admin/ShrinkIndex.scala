package com.sksamuel.elastic4s_6_2_11.admin

case class ShrinkIndex(source: String,
                       target: String,
                       waitForActiveShards: Option[Int] = None,
                       settings: Map[String, String] = Map.empty) {

  def settings(map: Map[String, String]): ShrinkIndex = copy(settings = settings)
}
