package com.sksamuel.elastic4s_6_2_11.tasks

import com.sksamuel.elastic4s_6_2_11.testkit.DockerTests
import org.scalatest.{FlatSpec, Matchers}

class TasksTest extends FlatSpec with DockerTests with Matchers {

  "list tasks" should "include all fields" in {

    val resp = http.execute {
      listTasks()
    }.await.right.get.result

    resp.nodes.head._2.roles shouldBe Seq("master", "data", "ingest")
    resp.nodes.head._2.tasks.values.forall(_.startTime.toMillis > 0) shouldBe true
  }

}
