package expressionproblemtagged3.interpreters

import expressionproblemtagged3.dsls.{Addition, Division, Expr, Multiplication}

object Expression {
  val dsl: Expr[Int] = new Expr[Int] {
    override def lit(el: Int): Option[Int] = Some(el)
  }
}

object Multiplication {
  val dsl: Multiplication[Int] = new Multiplication[Int] {
    override def mult(a1: Option[Int], a2: Option[Int]): Option[Int] =
      a1.zip(a2).map {
        case (a1, a2) => a1 * a2
      }
  }
}

object Addition {
  val dsl: Addition[Int] = new Addition[Int] {
    override def add(a1: Option[Int], a2: Option[Int]): Option[Int] =
      a1.zip(a2).map {
        case (a1, a2) => a1 + a2
      }
  }
}

object Division {
  val dsl: Division[Int] =
    new Division[Int] {
      override def divide(a1: Option[Int], a2: Option[Int]): Option[Int] =
        a1.zip(a2).flatMap {
          case (a1, 0) =>
            None

          case (a1, a2) =>
            if (a1 % a2 == 0)
              Some(a1 / a2)
            else
              None
        }
    }}