package com.sksamuel.elastic4s_6_2_11

import com.sksamuel.elastic4s_6_2_11.admin.IndexAdminApi
import com.sksamuel.elastic4s_6_2_11.alias.AliasesApi
import com.sksamuel.elastic4s_6_2_11.analyzers.{AnalyzerApi, NormalizerApi, TokenizerApi}
import com.sksamuel.elastic4s_6_2_11.bulk.BulkApi
import com.sksamuel.elastic4s_6_2_11.cat.CatsApi
import com.sksamuel.elastic4s_6_2_11.cluster.ClusterApi
import com.sksamuel.elastic4s_6_2_11.count.CountApi
import com.sksamuel.elastic4s_6_2_11.delete.DeleteApi
import com.sksamuel.elastic4s_6_2_11.explain.ExplainApi
import com.sksamuel.elastic4s_6_2_11.get.GetApi
import com.sksamuel.elastic4s_6_2_11.indexes.admin.{ForceMergeApi, IndexRecoveryApi}
import com.sksamuel.elastic4s_6_2_11.indexes.{CreateIndexApi, DeleteIndexApi, IndexApi, IndexTemplateApi}
import com.sksamuel.elastic4s_6_2_11.locks.LocksApi
import com.sksamuel.elastic4s_6_2_11.mappings.MappingApi
import com.sksamuel.elastic4s_6_2_11.mappings.dynamictemplate.DynamicTemplateApi
import com.sksamuel.elastic4s_6_2_11.nodes.NodesApi
import com.sksamuel.elastic4s_6_2_11.reindex.ReindexApi
import com.sksamuel.elastic4s_6_2_11.script.ScriptApi
import com.sksamuel.elastic4s_6_2_11.searches._
import com.sksamuel.elastic4s_6_2_11.searches.aggs.AggregationApi
import com.sksamuel.elastic4s_6_2_11.searches.aggs.pipeline.PipelineAggregationApi
import com.sksamuel.elastic4s_6_2_11.searches.collapse.CollapseApi
import com.sksamuel.elastic4s_6_2_11.searches.queries.funcscorer.ScoreApi
import com.sksamuel.elastic4s_6_2_11.searches.sort.SortApi
import com.sksamuel.elastic4s_6_2_11.searches.suggestion.SuggestionApi
import com.sksamuel.elastic4s_6_2_11.settings.SettingsApi
import com.sksamuel.elastic4s_6_2_11.snapshots.SnapshotApi
import com.sksamuel.elastic4s_6_2_11.task.TaskApi
import com.sksamuel.elastic4s_6_2_11.termvectors.TermVectorApi
import com.sksamuel.elastic4s_6_2_11.update.UpdateApi
import com.sksamuel.elastic4s_6_2_11.validate.ValidateApi

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

// contains all the syntactic definitions
trait ElasticApi
    extends ElasticImplicits
    with AliasesApi
    with AggregationApi
    with AnalyzerApi
    with BulkApi
    with CatsApi
    with CreateIndexApi
    with ClusterApi
    with CollapseApi
    with CountApi
    with DeleteApi
    with DeleteIndexApi
    with DynamicTemplateApi
    with ExistsApi
    with ExplainApi
    with ForceMergeApi
    with GetApi
    with HighlightApi
    with IndexApi
    with IndexAdminApi
    with IndexRecoveryApi
    with IndexTemplateApi
    with LocksApi
    with MappingApi
    with NodesApi
    with NormalizerApi
    with QueryApi
    with PipelineAggregationApi
    with ReindexApi
    with ScriptApi
    with ScoreApi
    with ScrollApi
    with SearchApi
    with SearchTemplateApi
    with SettingsApi
    with SnapshotApi
    with SortApi
    with SuggestionApi
    with TaskApi
    with TermVectorApi
    with TokenizerApi
    with TypesApi
    with UpdateApi
    with ValidateApi {

  implicit class RichFuture[T](future: Future[T]) {
    def await(implicit duration: Duration = 60.seconds): T = Await.result(future, duration)
  }
}

object ElasticApi extends ElasticApi
