package expressionproblemtagged2.dsls

trait Expr[A] {
  def lit(e1:Int):A
}

trait Addition[A] {
  def add(e1:A, e2:A):A
}

trait Multiplication[A] {
  def mult(e1:A, e2:A):A
}