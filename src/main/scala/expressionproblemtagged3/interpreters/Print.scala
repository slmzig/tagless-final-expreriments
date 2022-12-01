package expressionproblemtagged3.interpreters

import expressionproblemtagged3.dsls.{Addition, Expr, Multiplication}

object PrintExpr {
    val dsl:Expr[String] = new Expr[String] {
      override def lit(e1: Int): Option[String] = Some(s"$e1")
    }
  }

object PrintMultiplication {
  val dsl:Multiplication[String] = new Multiplication[String]{
    override def mult(e1: Option[String], e2: Option[String]): Option[String] =
    e1.zip(e2).map {
      case (a1, a2) => s"$e1 * $e2"
    }
  }
}

object PrintAddition {
  val dsl:Addition[String] = new Addition[String] {
    override def add(e1: Option[String], e2: Option[String]): Option[String] =
      e1.zip(e2).map {
        case (a1, a2) => s"$e1 + $e2"
      }
  }
}