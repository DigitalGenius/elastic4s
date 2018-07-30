package com.sksamuel.elastic4s_6_2_11.circe

import org.scalatest.{GivenWhenThen, Matchers, WordSpec}

case class Place(id: Int, name: String)
case class Cafe(name: String, place: Place)

class CodecDerivationTest extends WordSpec with Matchers with GivenWhenThen {

  "A derived HitReader instance" should {

    "be implicitly found if circe.generic.auto is in imported" in {
      """
        import io.circe.generic.auto._
        import com.sksamuel.elastic4s_6_2_11.HitReader
        implicitly[HitReader[Cafe]]
      """ should compile
    }

    "not compile if no decoder is in scope" in {
      """
        import com.sksamuel.elastic4s_6_2_11.HitReader
        implicitly[HitReader[Cafe]]
      """ shouldNot compile
    }
  }

  "A derived AggReader instance" should {
    "be implicitly found if circe.generic.auto is in imported" in {
      """
        import io.circe.generic.auto._
        import com.sksamuel.elastic4s_6_2_11.AggReader
        implicitly[AggReader[Cafe]]
      """ should compile
    }

    "not compile if no decoder is in scope" in {
      """
        import com.sksamuel.elastic4s_6_2_11.AggReader
        implicitly[AggReader[Cafe]]
      """ shouldNot compile
    }
  }

  "A derived Indexable instance" should {
    "be implicitly found if circe.generic.auto is in imported" in {
      """
        import io.circe.generic.auto._
        import com.sksamuel.elastic4s_6_2_11.Indexable
        implicitly[Indexable[Cafe]]
      """ should compile
    }

    "not compile if no decoder is in scope" in {
      """
        import com.sksamuel.elastic4s_6_2_11.Indexable
        implicitly[Indexable[Cafe]]
      """ shouldNot compile
    }
  }

}