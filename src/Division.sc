sealed trait DivisionResult

final case class Finite(result: Int) extends DivisionResult

case object Infinite extends DivisionResult

object divide {
  def apply(v1: Int, v2: Int): DivisionResult = if (v2 == 0) Infinite else Finite(v1 / v2)
}

val x = divide(10, 2)
val y = divide(1, 0)

def foo(v1: Int, v2: Int) =
  divide(v1, v2) match {
    case Finite(result) => s"Result is $result"
    case Infinite => "Result is infinity"
  }

foo(15, 5)
foo(7, 0)