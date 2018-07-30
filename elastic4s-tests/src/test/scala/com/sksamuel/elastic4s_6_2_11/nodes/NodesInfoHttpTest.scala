package com.sksamuel.elastic4s_6_2_11.nodes

import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{Matchers, WordSpec}

class NodesInfoHttpTest extends WordSpec with Matchers with DockerTests {

  "node info request" should {
    "return node information" in {
      val nodes = http.execute {
        nodeInfo()
      }.await.right.get.result

      nodes.clusterName should be("docker-cluster")
      nodes.nodes.values.toSeq.head.os.availableProcessors > 0 shouldBe true
    }
  }
}
