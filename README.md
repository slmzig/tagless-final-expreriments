### the idea of this project
- the expression problem
- learn how to solve expression problem by writing dsl with
  - initial embedding
  - tagged initial embedding
  - tagless initial embedding
- the final aim is basicly to practic final tagless approach

### project structure
I split this project into some parts:
  - expressionproblem
    - creating algebra
    - add addition operation
    - add expression itself
    - evaluate expression with pattern matching and recursion
    - print expression with pattern matching and recursion
  - expressionproblemtagged
    - start thinking about approach
    - trying to add new operation multiply
    - found that adding new operation lead to rewriting function with main logic
    - I mean that after adding new operation I should change my function with pattern matching all the time
    - so I refactor a bit logic
    - I did it in a type classes style
      - dsl (type class)
      - interpreters (type class instances)
      - program (type class interfaces)
  - expressionproblemtagged2
    - go further in refactoring
    - each operation move to separate trait
    - so now we have separate type class and interpreter for each operation
    - in this case adding new operation will not lead to changing existing code
    - so no changing code only adding new one
  - expressionproblemtagged3
    - in this step I added division
    - and I found that this operation may return error
    - so I should change return type from `A` for something like Option[A]
    - but by adding return type to Option[A] I found that
      - my solution now is not composable and decomposable
      - so I can not do something like
      - ```scala
           add(lit(23),lit(23))
        ```
      - I cant change only return type of division
      - to make it composable I should change all types (param and return) in all operations
      - ```scala
        trait Addition[A] {
        def add(e1:Option[A], e2:Option[A]):Option[A]
        }
        ```
       - so good so far but what if I will decide to change type from option to either ??? 
  - taglessfinal
    - in this step I've added higher kinded type F[_]
    - now my solution become more general
    - now I can change not only element type but container type

I started solving expression problem in first  `expressionproblem` directory
and I tried to make solution better adding new feature and refactoring code