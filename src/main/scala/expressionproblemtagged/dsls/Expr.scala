package expressionproblemtagged.dsls

   trait Expr[A] {
    def lit(e1:Int):A
    def add(e1:A, e2:A):A
  }