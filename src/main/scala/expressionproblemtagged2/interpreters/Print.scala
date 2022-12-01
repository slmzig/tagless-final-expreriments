package expressionproblemtagged2.interpreters

import expressionproblemtagged2.dsls.{Addition, Expr, Multiplication}

object PrintExpr {
    val dsl:Expr[String] = new Expr[String] {
      override def lit(e1: Int): String = s"$e1"
    }
  }

object PrintMultiplication {
  val dsl:Multiplication[String] = new Multiplication[String]{
    override def mult(e1: String, e2: String): String = s"($e1 * $e2)"
  }
}

object PrintAddition {
  val dsl:Addition[String] = new Addition[String] {
    override def add(e1: String, e2: String): String = s"$e1 + $e2"
  }
}