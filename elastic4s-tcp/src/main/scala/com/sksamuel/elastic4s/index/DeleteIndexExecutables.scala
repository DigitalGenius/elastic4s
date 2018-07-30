package com.sksamuel.elastic4s_6_2_11.index

import com.sksamuel.elastic4s_6_2_11.Executable
import com.sksamuel.elastic4s_6_2_11.indexes.DeleteIndex
import org.elasticsearch.action.ActionListener
import org.elasticsearch.action.admin.indices.delete.{DeleteIndexRequest, DeleteIndexResponse}
import org.elasticsearch.client.Client

import scala.concurrent.Future

trait DeleteIndexExecutables {
  implicit object DeleteIndexDefinitionExecutable
      extends Executable[DeleteIndex, DeleteIndexResponse, DeleteIndexResponse] {
    override def apply(c: Client, t: DeleteIndex): Future[DeleteIndexResponse] = {
      val f = c.admin().indices().delete(new DeleteIndexRequest(t.indexes: _*), _: ActionListener[DeleteIndexResponse])
      injectFuture(f)
    }
  }
}
