package com.sksamuel.elastic4s_6_2_11

sealed trait Operator
object Operator {

  def valueOf(str: String): Operator = str.toLowerCase match {
    case "or"  => Or
    case "and" => And
  }

  case object And extends Operator
  case object Or  extends Operator

  def AND = And
  def OR  = Or
}
