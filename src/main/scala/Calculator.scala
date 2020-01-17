import scala.math.sqrt

object Calculator extends App {
  sealed trait Expression {
    final def eval: CalculationResult =
      this match {
        case Number(value) => SuccessQ(value)
        case Addition(left, right) => calcTwoOperators(left, right, (a, b) => SuccessQ(a + b))
        case Subtraction(left, right) => calcTwoOperators(left, right, (a, b) => SuccessQ(a - b))
        case Multiply(left, right) => calcTwoOperators(left, right, (a, b) => SuccessQ(a * b))
        case Division(left, right) => calcTwoOperators(left, right, (num, den) => if (den != 0) SuccessQ(num / den) else FailureQ("Division by zero"))
        case SquareRoot(expr) =>
          expr.eval match {
            case SuccessQ(value) => if (value >= 0) SuccessQ(sqrt(value)) else FailureQ("Square root of negative number")
            case fail => fail
          }
      }

    private def calcTwoOperators(left: Expression, right: Expression, f: (Double, Double) => CalculationResult): CalculationResult =
      left.eval match {
        case SuccessQ(leftValue) =>
          right.eval match {
            case SuccessQ(rightValue) => f(leftValue, rightValue)
            case fail => fail
          }
        case fail => fail
      }
  }
  final case class Addition(left: Expression, right: Expression) extends Expression
  final case class Subtraction(left: Expression, right: Expression) extends Expression
  final case class Multiply(left: Expression, right: Expression) extends Expression
  final case class Division(left: Expression, right: Expression) extends Expression
  final case class SquareRoot(expr: Expression) extends Expression
  final case class Number(value: Double) extends Expression

  sealed trait CalculationResult
  final case class SuccessQ(value: Double) extends CalculationResult
  final case class FailureQ(reason: String) extends CalculationResult

  sealed trait Sum[+A, +B] {
    final def fold[C](failure: A => C)(success: B => C): C =
      this match {
        case Failure(value) => failure(value)
        case Success(value) => success(value)
      }

    final def flatMap[AA >: A, C](f: B => Sum[AA, C]): Sum[AA, C] =
      this match {
        case Failure(value) => Failure(value)
        case Success(value) => f(value)
      }

    final def map[C](f: B => C): Sum[A, C] =
      this match {
        case Failure(value) => Failure(value)
        case Success(value) => Success(f(value))
      }
  }
  final case class Failure[A](a: A) extends Sum[A, Nothing]
  final case class Success[B](b: B) extends Sum[Nothing, B]

  val expr = Addition(Division(Multiply(SquareRoot(Addition(Number(9), Subtraction(Addition(Number(4), Number(6)), Number(3)))), Number(-1)), Number(10)), Number(100))
  println(expr.eval)
}
