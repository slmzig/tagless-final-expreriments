package taglessfinal.programs

import cats.Id
import taglessfinal.dsls.{Addition, Division, Expr, Multiplication}
import taglessfinal.interpreters.{Addition, DivisionError, DivisionOption, Expr, Multiplication}
import cats.implicits._

import scala.util.Try

trait Program[F[_], A] {
  def run: F[A]
}

object Program {
  def dsl[F[_], A](expr: Expr[F, A]): Program[F, A] = new Program[F, A] {
    override def run: F[A] = {
      import expr._
      lit(1)
    }
  }
}

object Program2 {
  def dsl[F[_], A](
                    expr: Expr[F, A],
                    addition: Addition[F, A]
                  ): Program[F, A] = new Program[F, A] {
    override def run: F[A] = {
      import expr._
      import addition._
      add(lit(1), lit(1))
    }
  }
}

object Program3 {
  def dsl[F[_], A](
                    expr: Expr[F, A],
                    addition: Addition[F, A],
                    multiplication: Multiplication[F, A],
                    division: Division[F, A]
                  ): Program[F, A] = new Program[F, A] {
    override def run: F[A] = {
      import expr._
      import addition._
      import division._
      divide(lit(1), lit(0))
    }
  }
}

object Main2 extends App {

  type EitherOr[A] = Either[String, A]

  println(Program.dsl[Option, Int](Expr.dsl).run)
  println(Program.dsl[Id, Int](Expr.dsl).run)
  println(Program.dsl[Try, Int](Expr.dsl).run)
  println(Program.dsl[EitherOr, Int](Expr.dsl).run)

  println("-" * 100)

  println(Program2.dsl[Option, Int](
    Expr.dsl,
    Addition.dsl
  ).run)

  println(Program2.dsl[Id, Int](
    Expr.dsl,
    Addition.dsl
  ).run)

  println(Program2.dsl[Try, Int](
    Expr.dsl,
    Addition.dsl
  ).run)

  println(Program2.dsl[EitherOr, Int](
    Expr.dsl,
    Addition.dsl
  ).run)

  println("-" * 100)

  println(Program3.dsl[Option, Int](
    Expr.dsl,
    Addition.dsl,
    Multiplication.dsl,
    DivisionOption.dsl
  ).run)

  println(Program3.dsl[EitherOr, Int](
    Expr.dsl,
    Addition.dsl,
    Multiplication.dsl,
    DivisionError.dsl
  ).run)
}
