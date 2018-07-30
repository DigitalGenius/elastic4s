package com.sksamuel.elastic4s_6_2_11.analyzers

abstract class Normalizer(val name: String)

// Pre-built normalizers still to be added
case class CustomNormalizer(override val name: String) extends Normalizer(name)
