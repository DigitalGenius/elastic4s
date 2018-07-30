package com.sksamuel.elastic4s_6_2_11.cat

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{FlatSpec, Matchers}

class CatNodesTest extends FlatSpec with Matchers with DockerTests {

  http.execute {
    bulk(
      indexInto("catnodes1/landmarks").fields("name" -> "hampton court palace"),
      indexInto("catnodes2/landmarks").fields("name" -> "hampton court palace")
    ).refresh(RefreshPolicy.Immediate)
  }.await

  "cats nodes" should "return all nodes" in {
    val result = http.execute {
      catNodes()
    }.await.right.get.result.head
    result.load_1m > 0 shouldBe true
    result.cpu > 0 shouldBe true
    result.heapPercent > 0 shouldBe true
    result.ramPercent > 0 shouldBe true
  }
}
