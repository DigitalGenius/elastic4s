package com.sksamuel.elastic4s_6_2_11.indexes

import com.sksamuel.elastic4s_6_2_11.analyzers._
import com.sksamuel.elastic4s_6_2_11.mappings.MappingDefinition

trait CreateIndexApi {

  def createIndex(name: String): CreateIndexDefinition = CreateIndexDefinition(name)

  def analyzers(analyzers: AnalyzerDefinition*) = new AnalyzersWrapper(analyzers)
  def tokenizers(tokenizers: Tokenizer*)        = new TokenizersWrapper(tokenizers)
  def filters(filters: TokenFilter*)            = new TokenFiltersWrapper(filters)

  def mapping(name: String): MappingDefinition = MappingDefinition(name)

  class AnalyzersWrapper(val analyzers: Iterable[AnalyzerDefinition])
  class TokenizersWrapper(val tokenizers: Iterable[Tokenizer])
  class TokenFiltersWrapper(val filters: Iterable[TokenFilter])
}
