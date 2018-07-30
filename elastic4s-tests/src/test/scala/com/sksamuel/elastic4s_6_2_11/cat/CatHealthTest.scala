package com.sksamuel.elastic4s_6_2_11.cat

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.http.ElasticDsl
import com.sksamuel.elastic4s_6_2_11.testkit.{DiscoveryLocalNodeProvider, DockerTests}
import org.scalatest.{FlatSpec, Matchers}

class CatHealthTest extends FlatSpec with Matchers with DockerTests {

  http.execute {
    bulk(
      indexInto("cathealth/landmarks").fields("name" -> "hampton court palace")
    ).refresh(RefreshPolicy.Immediate)
  }.await

  "cat health" should "return cluster health" in {
    http.execute {
      catHealth()
    }.await.right.get.result.cluster shouldBe "docker-cluster"
  }

}
