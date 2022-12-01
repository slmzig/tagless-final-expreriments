package expressionproblemtagged.interpreters

import expressionproblemtagged.dsls.Expr

object Eval {
  val dsl: Expr[Int] = new Expr[Int] {
    override def add(e1: Int, e2: Int): Int = e1 + e2

    override def lit(e1: Int): Int = e1
  }
}