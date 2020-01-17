import scala.math.sqrt

object Calculator extends App {
  sealed trait Expression {
    final def eval: Sum[String, Double] =
      this match {
        case Number(value) => Success(value)
        case Addition(left, right) => lift2(left, right, (l, r) => Success(l + r))
        case Subtraction(left, right) => lift2(left, right, (l, r) => Success(l - r))
        case Multiply(left, right) => lift2(left, right, (l, r) => Success(l * r))
        case Division(left, right) => lift2(left, right, (l, r) => if (r != 0) Success(l / r) else Failure("Division by zero"))
        case SquareRoot(value) => value.eval.flatMap { v =>
          if (v > 0) Success(sqrt(v)) else Failure("Square root of negative number")
        }
      }

    private final def lift2(left: Expression, right: Expression, f: (Double, Double) => Sum[String, Double]): Sum[String, Double] =
      left.eval.flatMap { l =>
        right.eval.flatMap { r =>
          f(l, r)
        }
      }
  }
  final case class Number(value: Double) extends Expression
  final case class Addition(left: Expression, right: Expression) extends Expression
  final case class Subtraction(left: Expression, right: Expression) extends Expression
  final case class Multiply(left: Expression, right: Expression) extends Expression
  final case class Division(left: Expression, right: Expression) extends Expression
  final case class SquareRoot(expr: Expression) extends Expression

  sealed trait Sum[+A, +B] {
    final def map[C](f: B => C): Sum[A, C] = flatMap(v => Success(f(v)))

    final def flatMap[AA >: A, C](f: B => Sum[AA, C]): Sum[AA, C] = fold[Sum[AA, C]](Failure(_))(f(_))

    final def fold[C](failure: A => C)(success: B => C): C =
      this match {
        case Failure(value) => failure(value)
        case Success(value) => success(value)
      }
  }
  final case class Failure[A](a: A) extends Sum[A, Nothing]
  final case class Success[B](b: B) extends Sum[Nothing, B]

  val expr = Addition(Division(Multiply(SquareRoot(Addition(Number(9), Subtraction(Addition(Number(4), Number(6)), Number(3)))), Number(-1)), Number(10)), Number(100))
  println(expr.eval)
}
