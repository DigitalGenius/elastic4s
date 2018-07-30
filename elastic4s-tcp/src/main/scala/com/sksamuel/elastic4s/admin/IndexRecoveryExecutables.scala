package com.sksamuel.elastic4s_6_2_11.admin

import com.sksamuel.elastic4s_6_2_11.Executable
import com.sksamuel.elastic4s_6_2_11.indexes.admin.IndexRecoveryDefinition
import org.elasticsearch.action.admin.indices.recovery.RecoveryResponse
import org.elasticsearch.client.Client

import scala.concurrent.Future

trait IndexRecoveryExecutables {

  implicit object IndexRecoveryDefinitionExecutable
      extends Executable[IndexRecoveryDefinition, RecoveryResponse, RecoveryResponse] {
    override def apply(c: Client, t: IndexRecoveryDefinition): Future[RecoveryResponse] = {
      val builder = c.admin().indices().prepareRecoveries(t.indices: _*)
      t.activeOnly.foreach(builder.setActiveOnly)
      t.detailed.foreach(builder.setDetailed)
      injectFuture(builder.execute(_))
    }
  }
}
