package expressionproblemtagged.programs

import expressionproblemtagged.dsls.Expr
import expressionproblemtagged.interpreters.{Eval, Print}

trait Program[Repr] {
  def repr:Repr
}

object Program {
  def dsl[Repr](expr: Expr[Repr]): Program[Repr] = new Program[Repr] {
    import expr._

    val repr: Repr = add(
      lit(23),
      lit(23)
    )
  }
}

object Main extends App {
  import Program._

  val evalR = Program.dsl[Int](Eval.dsl).repr
  val printR = Program.dsl[String](Print.dsl).repr

  println(evalR)
  println(printR)
}