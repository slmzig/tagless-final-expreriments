package expressionproblemtagged.interpreters

import expressionproblemtagged.dsls.Expr

object Print {
    val dsl:Expr[String] = new Expr[String] {
      override def add(e1: String, e2: String): String = s"$e1 + $e2"
      override def lit(e1: Int): String = s"$e1"
    }
  }