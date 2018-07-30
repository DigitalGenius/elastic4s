package com.sksamuel.elastic4s_6_2_11.searches.sort

import com.sksamuel.elastic4s_6_2_11.script.ScriptDefinition
import com.sksamuel.elastic4s_6_2_11.searches.queries.QueryDefinition
import com.sksamuel.exts.OptionImplicits._

case class ScriptSortDefinition(script: ScriptDefinition,
                                scriptSortType: ScriptSortType,
                                sortMode: Option[SortMode] = None,
                                nestedPath: Option[String] = None,
                                order: Option[SortOrder] = None,
                                nestedFilter: Option[QueryDefinition] = None)
    extends SortDefinition {

  def mode(mode: String): ScriptSortDefinition   = sortMode(SortMode.valueOf(mode.toUpperCase))
  def mode(mode: SortMode): ScriptSortDefinition = copy(sortMode = mode.some)

  def sortMode(mode: String): ScriptSortDefinition   = sortMode(SortMode.valueOf(mode.toUpperCase))
  def sortMode(mode: SortMode): ScriptSortDefinition = copy(sortMode = mode.some)

  def nestedPath(path: String): ScriptSortDefinition             = copy(nestedPath = path.some)
  def nestedFilter(query: QueryDefinition): ScriptSortDefinition = copy(nestedFilter = query.some)

  def order(order: SortOrder): ScriptSortDefinition     = copy(order = order.some)
  def sortOrder(order: SortOrder): ScriptSortDefinition = copy(order = order.some)

  def asc(): ScriptSortDefinition  = sortOrder(SortOrder.Asc)
  def desc(): ScriptSortDefinition = sortOrder(SortOrder.Desc)
}
