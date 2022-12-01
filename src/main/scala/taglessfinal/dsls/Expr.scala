package taglessfinal.dsls

trait Expr[F[_], A] {
  def lit(e1:Int):F[A]
}

trait Addition[F[_], A] {
  def add(e1:F[A], e2:F[A]):F[A]
}

trait Multiplication[F[_], A] {
  def mult(e1:F[A], e2:F[A]):F[A]
}

trait Division[F[_], A] {
  def divide(e1:F[A], e2:F[A]):F[A]
}