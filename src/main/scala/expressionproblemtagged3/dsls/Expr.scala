package expressionproblemtagged3.dsls

trait Expr[A] {
  def lit(e1:Int):Option[A]
}

trait Addition[A] {
  def add(e1:Option[A], e2:Option[A]):Option[A]
}

trait Multiplication[A] {
  def mult(e1:Option[A], e2:Option[A]):Option[A]
}

trait Division[A] {
  def divide(e1:Option[A], e2:Option[A]):Option[A]
}