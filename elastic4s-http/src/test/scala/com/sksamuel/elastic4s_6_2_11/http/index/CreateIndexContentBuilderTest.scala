package com.sksamuel.elastic4s_6_2_11.http.index

import com.sksamuel.elastic4s_6_2_11.indexes.{CreateIndexDefinition, IndexAliasDefinition}
import com.sksamuel.elastic4s_6_2_11.searches.queries.PrefixQuery
import org.scalatest.{FunSuite, Matchers}

class CreateIndexContentBuilderTest extends FunSuite with Matchers {

  test("create index should include aliases when set") {
    val create = CreateIndexDefinition("myindex", aliases = Set(IndexAliasDefinition("alias1", None), IndexAliasDefinition("alias2", Option(PrefixQuery("myfield", "pre")))))
    CreateIndexContentBuilder(create).string shouldBe
      """{"aliases":{"alias1":{},"alias2":{"filter":{"prefix":{"myfield":{"value":"pre"}}}}}}"""
  }
}
