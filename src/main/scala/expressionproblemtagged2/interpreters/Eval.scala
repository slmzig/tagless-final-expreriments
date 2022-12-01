package expressionproblemtagged2.interpreters

import expressionproblemtagged2.dsls.{Addition, Expr, Multiplication}

object Expression {
  val dsl: Expr[Int] = new Expr[Int] {
    override def lit(e1: Int): Int = e1
  }
}

object Multiplication {
  val dsl: Multiplication[Int] = new Multiplication[Int] {
    override def mult(e1: Int, e2: Int): Int = e1 * e2
  }
}

object Addition {
  val dsl: Addition[Int] = new Addition[Int] {
    override def add(e1: Int, e2: Int): Int = e1 + e2
  }
}