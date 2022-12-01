package expressionproblemtagged3.programs

import expressionproblemtagged3.dsls.{Addition, Expr, Multiplication}
import expressionproblemtagged3.interpreters._

trait Program[A] {
  def run: Option[A]
}

object Program {
  def dsl[A](
              expr: Expr[A],
              mult: Multiplication[A],
              addition: Addition[A]
            ): Program[A] = new Program[A] {

    import addition._
    import expr._


    val run = add(
      lit(23),
      lit(23)
    )
  }
}

object Main extends App {

  val evalR = Program.dsl[Int](
    Expression.dsl,
    Multiplication.dsl,
    Addition.dsl
  ).run

  val printR = Program.dsl[String](
    PrintExpr.dsl,
    PrintMultiplication.dsl,
    PrintAddition.dsl
  ).run

  println(evalR)
  println(printR)
}