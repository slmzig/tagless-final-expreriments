package expressionproblem

object ExpressionProblem extends App {

  sealed trait Expr

  case class Add(l: Expr, r: Expr) extends Expr

  case class Lit(i: Int) extends Expr

  def eval(expr: Expr): Int = {
    expr match {
      case Lit(i) => i
      case Add(l, r) => eval(l) + eval(r)
    }
  }

  def print(expr: Expr): String = {
    expr match {
      case Lit(i) => s"$i"
      case Add(l, r) => s"${print(l)} + ${print(r)}"
    }
  }

  val evalResult = eval(
    Add(Lit(2), Lit(3))
  )

  val printResult = print(
    Add(Lit(2), Lit(3))
  )

  println(evalResult)
  println(printResult)

  // hard to add new operation for example Multiple
}

object ExpressionProblem2 extends App {

  sealed trait Expr

  case class Add(l: Expr, r: Expr) extends Expr

  // we add multiple here
  case class Mult(l: Expr, r: Expr) extends Expr

  case class Lit(i: Int) extends Expr

  def eval(expr: Expr): Int = {
    expr match {
      case Lit(i) => i
      case Add(l, r) => eval(l) + eval(r)
      // we need to change also our code here
      case Mult(l, r) => eval(l) * eval(r)
    }
  }

  def print(expr: Expr): String = {
    expr match {
      case Lit(i) => s"$i"
      case Add(l, r) => s"${print(l)} + ${print(r)}"
      // and we need to change also our code here
      case Mult(l, r) => s"(${print(l)} * ${print(r)})"
    }
  }

  val evalResult = eval(
    Mult(Lit(3), Lit(3))
  )

  val evalResult2 = eval(
    Mult(Add(Lit(2), Lit(2)), Lit(3))
  )

  val evalResult3 = eval(
    Add(Mult(Lit(2), Lit(3)), Lit(3))
  )

  val printResult2 = print(
    Mult(Add(Lit(2), Lit(2)), Lit(3))
  )

  val printResult3 = print(
    Add(Mult(Lit(2), Lit(3)), Lit(3))
  )

  println(evalResult)
  println(evalResult2)
  println(printResult2)
  println(evalResult3)
  println(printResult3)
}
