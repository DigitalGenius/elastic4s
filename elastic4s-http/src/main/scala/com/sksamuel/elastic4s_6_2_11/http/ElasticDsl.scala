package com.sksamuel.elastic4s_6_2_11.http

import cats.Show
import com.sksamuel.elastic4s_6_2_11.ElasticApi
import com.sksamuel.elastic4s_6_2_11.http.bulk.BulkImplicits
import com.sksamuel.elastic4s_6_2_11.http.cat.CatImplicits
import com.sksamuel.elastic4s_6_2_11.http.cluster.ClusterImplicits
import com.sksamuel.elastic4s_6_2_11.http.count.CountImplicits
import com.sksamuel.elastic4s_6_2_11.http.delete.DeleteImplicits
import com.sksamuel.elastic4s_6_2_11.http.explain.ExplainImplicits
import com.sksamuel.elastic4s_6_2_11.http.get.GetImplicits
import com.sksamuel.elastic4s_6_2_11.http.index._
import com.sksamuel.elastic4s_6_2_11.http.index.admin.IndexAdminImplicits
import com.sksamuel.elastic4s_6_2_11.http.index.alias.IndexAliasImplicits
import com.sksamuel.elastic4s_6_2_11.http.index.mappings.MappingExecutables
import com.sksamuel.elastic4s_6_2_11.http.locks.LocksImplicits
import com.sksamuel.elastic4s_6_2_11.http.nodes.NodesImplicits
import com.sksamuel.elastic4s_6_2_11.http.reindex.ReindexImplicits
import com.sksamuel.elastic4s_6_2_11.http.search.template.SearchTemplateImplicits
import com.sksamuel.elastic4s_6_2_11.http.search.{SearchImplicits, SearchScrollImplicits}
import com.sksamuel.elastic4s_6_2_11.http.settings.SettingsImplicits
import com.sksamuel.elastic4s_6_2_11.http.snapshots.SnapshotHttpImplicits
import com.sksamuel.elastic4s_6_2_11.http.task.TaskImplicits
import com.sksamuel.elastic4s_6_2_11.http.termvectors.TermVectorsExecutables
import com.sksamuel.elastic4s_6_2_11.http.update.UpdateImplicits
import com.sksamuel.elastic4s_6_2_11.http.validate.ValidateImplicits
import com.sksamuel.exts.Logging

trait ElasticDsl
    extends ElasticApi
    with Logging
    with BulkImplicits
    with CatImplicits
    with CountImplicits
    with ClusterImplicits
    with DeleteImplicits
    with ExistsImplicits
    with ExplainImplicits
    with GetImplicits
    with IndexImplicits
    with IndexAdminImplicits
    with IndexAliasImplicits
    with IndexStatsImplicits
    with IndexTemplateImplicits
    with LocksImplicits
    with MappingExecutables
    with NodesImplicits
    with ReindexImplicits
    with RolloverImplicits
    with SearchImplicits
    with SearchTemplateImplicits
    with SearchScrollImplicits
    with SettingsImplicits
    with SnapshotHttpImplicits
    with UpdateImplicits
    with TaskImplicits
    with TermVectorsExecutables
    with ValidateImplicits {
  implicit class RichRequest[T](req: T) {
    def show(implicit show: Show[T]): String = show.show(req)
  }
}

object ElasticDsl extends ElasticDsl
