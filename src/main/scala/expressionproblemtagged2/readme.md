- in this part I will try to add multiplication
- and if I will add new method in `Expr` trait
- I will get a compile error
- because it will break all interpreters
- so how to do in without errors?

```scala
trait Expr[A] {
    def lit(e1:Int):A
    def add(e1:A, e2:A):A
    // this was added
    def mult(e1:A, e2:A):A
  }
/*
object creation impossible.
  Missing implementation for member of trait Expr:
```

- to solve this we can split as:

```scala
trait Expr[A] {
  def lit(e1:Int):A
}

trait Addition[A] {
  def add(e1:A, e2:A):A
}

trait Multiplication[A] {
  def mult(e1:A, e2:A):A
}
```

- and separate interpreters for each 
```scala
object Expression {
  val dsl: Expr[Int] = new Expr[Int] {
    override def lit(e1: Int): Int = e1
  }
}

object Multiplication {
  val dsl: Multiplication[Int] = new Multiplication[Int] {
    override def mult(e1: Int, e2: Int): Int = e1 * e2
  }
}

object Addition {
  val dsl: Addition[Int] = new Addition[Int] {
    override def add(e1: Int, e2: Int): Int = e1 + e2
  }
}
```
- such approach give us:
  - we are not changing code
  - we just adding a new one
  - no recompilation of all classes
  - no compile error