package com.sksamuel.elastic4s_6_2_11.index.admin

import com.sksamuel.elastic4s_6_2_11.Executable
import com.sksamuel.elastic4s_6_2_11.indexes.admin.ForceMergeDefinition
import org.elasticsearch.action.admin.indices.forcemerge.ForceMergeResponse
import org.elasticsearch.client.Client

import scala.concurrent.Future

trait ForceMergeExecutables {

  implicit object ForceMergeExecutable
      extends Executable[ForceMergeDefinition, ForceMergeResponse, ForceMergeResponse] {
    override def apply(c: Client, t: ForceMergeDefinition): Future[ForceMergeResponse] = {
      val builder = c.admin.indices.prepareForceMerge(t.indexes: _*)
      t.flush.foreach(builder.setFlush)
      t.onlyExpungeDeletes.foreach(builder.setOnlyExpungeDeletes)
      t.maxSegments.foreach(builder.setMaxNumSegments)
      injectFuture(builder.execute(_))
    }
  }

}
