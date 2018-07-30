package com.sksamuel.elastic4s_6_2_11.cat

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{FlatSpec, Matchers}

class CatMasterTest extends FlatSpec with Matchers with DockerTests {

  http.execute {
    bulk(
      indexInto("catmaster/landmarks").fields("name" -> "hampton court palace")
    ).refresh(RefreshPolicy.Immediate)
  }.await


  "cat master" should "return master node info" in {
    val result = http.execute {
      catMaster()
    }.await.right.get.result

    result.host should not be null
    result.id should not be null
  }

}
