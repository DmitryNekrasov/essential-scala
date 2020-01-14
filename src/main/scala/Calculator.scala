import scala.math.sqrt

object Calculator extends App {
  sealed trait Expression {
    final def eval: CalculationResult =
      this match {
        case Number(value) => Success(value)
        case Addition(left, right) => calcTwoOperators(left, right, (a, b) => Success(a + b))
        case Subtraction(left, right) => calcTwoOperators(left, right, (a, b) => Success(a - b))
        case Multiply(left, right) => calcTwoOperators(left, right, (a, b) => Success(a * b))
        case Division(left, right) => calcTwoOperators(left, right, (num, den) => if (den != 0) Success(num / den) else Failure("Division by zero"))
        case SquareRoot(expr) =>
          expr.eval match {
            case Success(value) => if (value >= 0) Success(sqrt(value)) else Failure("Square root of negative number")
            case fail => fail
          }
      }

    private def calcTwoOperators(left: Expression, right: Expression, f: (Double, Double) => CalculationResult): CalculationResult =
      left.eval match {
        case Success(leftValue) =>
          right.eval match {
            case Success(rightValue) => f(leftValue, rightValue)
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
  final case class Success(value: Double) extends CalculationResult
  final case class Failure(reason: String) extends CalculationResult

  val expr = Addition(Division(Multiply(SquareRoot(Addition(Number(9), Subtraction(Addition(Number(4), Number(6)), Number(3)))), Number(-1)), Number(10)), Number(100))
  println(expr.eval)
}
