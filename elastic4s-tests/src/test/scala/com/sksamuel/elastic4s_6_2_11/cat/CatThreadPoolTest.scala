package com.sksamuel.elastic4s_6_2_11.cat

import com.sksamuel.elastic4s_6_2_11.RefreshPolicy
import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{FlatSpec, Matchers}

class CatThreadPoolTest extends FlatSpec with Matchers with DockerTests {

  http.execute {
    bulk(
      indexInto("amoonshapedpool1/landmarks").fields("name" -> "hampton court palace"),
      indexInto("amoonshapedpool2/landmarks").fields("name" -> "hampton court palace")
    ).refresh(RefreshPolicy.Immediate)
  }.await

  "cat thread pool" should "return all pools" in {
    val pools = http.execute {
      catThreadPool()
    }.await.right.get.result.map(_.name).toSet
    Set("refresh", "bulk", "listener", "warmer", "generic", "fetch_shard_store", "snapshot", "force_merge", "management", "flush", "get", "fetch_shard_started", "index", "search").foreach { pool =>
      pools.contains(pool) shouldBe true
    }
  }

}
