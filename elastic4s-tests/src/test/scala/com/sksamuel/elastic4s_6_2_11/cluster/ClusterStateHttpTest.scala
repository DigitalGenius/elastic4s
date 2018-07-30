package com.sksamuel.elastic4s_6_2_11.cluster

import com.sksamuel.elastic4s_6_2_11.http.cluster.ClusterStateResponse.Index
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{Matchers, WordSpec}

import scala.util.Try

class ClusterStateHttpTest extends WordSpec with Matchers with DockerTests {

  private val indexname = "clusterstatetest"

  Try {
    http.execute {
      deleteIndex(indexname)
    }.await
  }

  http.execute {
    createIndex(indexname)
      .shards(1)
      .replicas(0)
      .waitForActiveShards(1)
  }.await

  "cluster state request" should {
    "return cluster state information" in {

      val state = http.execute {
        clusterState()
      }.await.right.get.result

      state.clusterName shouldBe "docker-cluster"
      state.compressedSizeInBytes > 0 shouldBe true
      state.stateUuid should not be null
      state.masterNode should not be null
      state.metadata.get.clusterUuid should not be null

      val indexMetadata = state.metadata.flatMap(m => m.indices.headOption).map(_._2).getOrElse(Index("closed", Seq.empty))

      indexMetadata should be(Index("open", Seq.empty))
    }
  }
}

