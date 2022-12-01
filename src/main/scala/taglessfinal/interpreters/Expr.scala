package taglessfinal.interpreters

import cats.{Applicative, Functor, Monad, MonadError, Monoid}
import cats.implicits.catsSyntaxTuple2Semigroupal
import cats.syntax.applicative._
import expressionproblemtagged3.interpreters.Division
import taglessfinal.dsls.{Addition, Division, Expr, Multiplication}

object Expr {
  def dsl[F[_] : Applicative, A]: Expr[F, Int] = new Expr[F, Int] {
    override def lit(e1: Int): F[Int] = {
      e1.pure[F]
    }
  }
}

object Addition {
  def dsl[F[_] : Applicative]: Addition[F, Int] = new Addition[F, Int] {
    def add(e1: F[Int], e2: F[Int]): F[Int] = {
      (e1, e2).mapN {
        case (e1: Int, e2: Int) => e1 + e2
      }
    }
  }
}

object Multiplication {
  def dsl[F[_] : Applicative]: Multiplication[F, Int] = new Multiplication[F, Int] {
    def mult(e1: F[Int], e2: F[Int]): F[Int] = {
      (e1, e2).mapN {
        case (e1: Int, e2: Int) => e1 * e2
      }
    }
  }
}

object Division {
  def dsl[F[_] : Applicative]: Multiplication[F, Int] = new Multiplication[F, Int] {
    def mult(e1: F[Int], e2: F[Int]): F[Int] = {
      (e1, e2).mapN {
        case (e1: Int, e2: Int) => e1 / e2
      }
    }
  }
}

object DivisionOption {

  type MonoidInt[F[_]] = Monoid[F[Int]]

  def dsl[F[_] : Monad : Functor : MonoidInt]: Division[F, Int] = new Division[F, Int] {
    override def divide(e1: F[Int], e2: F[Int]): F[Int] = {
      import cats.syntax.flatMap._
      import cats.syntax.functor._

      def checkErrors(value1: Int, value2: Int): F[Int] = {
        if (value2 == 0) Monoid[F[Int]].empty
        else Monad[F].pure[Int](value1 / value2)
      }

      for {
        value1 <- e1
        value2 <- e2
        result <- checkErrors(value1, value2)
      } yield result
    }
  }
}

object DivisionError {

  type ErrorOr[F[_]] = MonadError[F, String]

  def dsl[F[_] : ErrorOr : Functor]: Division[F, Int] = new Division[F, Int] {
    override def divide(e1: F[Int], e2: F[Int]): F[Int] = {
      import cats.syntax.flatMap._
      import cats.syntax.functor._

      def checkErrors(value1: Int, value2: Int): F[Int] = {
        if (value2 == 0) MonadError[F, String].raiseError[Int]("divide by zero")
        else MonadError[F, String].pure[Int](value1 / value2)
      }

      for {
        value1 <- e1
        value2 <- e2
        result <- checkErrors(value1, value2)
      } yield result
    }
  }
}