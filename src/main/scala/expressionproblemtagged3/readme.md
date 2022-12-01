- in this part we will try to add `Divide` operation
- and we will face with the problem because divide operation can return error
- for example if we will try to devide by 0
- so we should change return type to 
```scala
trait Divide[A] {
  def div(el1:A, el2:A): Option[A]
}
```
- and if we change to Option we will face with another problem
- Option is different result type
- all another type are `A`
- so this will lead great refactoring
- we should change all result type and params type from A to Option[A]
- to be able to compose and decompose our operations

```scala
trait Expr[A] {
  def lit(e1:Int):Option[A]
}

trait Addition[A] {
  def add(e1:Option[A], e2:Option[A]):Option[A]
}

trait Multiplication[A] {
  def mult(e1:Option[A], e2:Option[A]):Option[A]
}

trait Divide[A] {
  def div(e1:Option[A], e2:Option[A]):Option[A]
}
```