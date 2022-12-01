package taglessfinal.programs

import cats.{Applicative, Functor, Monad, MonadError, Monoid}

object Main extends App {

  trait Expr[F[_], A] {
    def lit(e1: Int): F[A]
  }

  trait Addition[F[_], A] {
    def add(e1: F[A], e2: F[A]): F[A]
  }

  trait Multiplication[F[_], A] {
    def mult(e1: F[A], e2: F[A]): F[A]
  }

  trait Division[F[_], A] {
    def divide(e1: F[A], e2: F[A]): F[A]
  }

  object Expr {

    import cats.implicits._

    def dsl[F[_] : Applicative, A]: Expr[F, Int] = new Expr[F, Int] {
      override def lit(e1: Int): F[Int] = {
        e1.pure[F]
      }
    }
  }

  object Addition {

    import cats.implicits._

    def dsl[F[_] : Applicative]: Addition[F, Int] = new Addition[F, Int] {
      def add(e1: F[Int], e2: F[Int]): F[Int] = {
        (e1, e2).mapN {
          case (e1: Int, e2: Int) => e1 + e2
        }
      }
    }
  }

  object Multiplication {

    import cats.implicits._

    def dsl[F[_] : Applicative]: Multiplication[F, Int] = new Multiplication[F, Int] {
      def mult(e1: F[Int], e2: F[Int]): F[Int] = {
        (e1, e2).mapN {
          case (e1: Int, e2: Int) => e1 * e2
        }
      }
    }
  }

  object Division {

    import cats.implicits._

    def dsl[F[_] : Applicative]: Division[F, Int] = new Division[F, Int] {
      override def divide(e1: F[Int], e2: F[Int]): F[Int] = {
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

  trait Program[F[_], A] {
    def run: F[A]
  }

  object Program {

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

  import cats.instances.option._

  println(Program.dsl[Option, Int](
    Expr.dsl,
    Addition.dsl,
    Multiplication.dsl,
    DivisionOption.dsl
  ).run)

  type EitherOr[A] = Either[String, A]

  println(Program.dsl[EitherOr, Int](
    Expr.dsl,
    Addition.dsl,
    Multiplication.dsl,
    DivisionError.dsl
  ).run)
}
