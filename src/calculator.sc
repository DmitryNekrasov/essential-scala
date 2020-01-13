sealed trait CalculationResult
final case class Succeed(result: Int) extends CalculationResult
final case class Fail(reason: String) extends CalculationResult

object Calculator {
  def +(calculationResult: CalculationResult, v2: Int): CalculationResult =
    calculationResult match {
      case Succeed(v1) => Succeed(v1 + v2)
      case fail => fail
    }

  def -(calculationResult: CalculationResult, v2: Int): CalculationResult =
    calculationResult match {
      case Succeed(v1) => Succeed(v1 - v2)
      case fail => fail
    }

  def *(calculationResult: CalculationResult, v2: Int): CalculationResult =
    calculationResult match {
      case Succeed(v1) => Succeed(v1 * v2)
      case fail => fail
    }

  def /(calculationResult: CalculationResult, v2: Int): CalculationResult =
    calculationResult match {
      case Succeed(v1) => if (v2 != 0) Succeed(v1 / v2) else Fail("Division by zero")
      case fail => fail
    }
}

assert(Calculator.+(Succeed(1), 1) == Succeed(2))
assert(Calculator.-(Succeed(1), 1) == Succeed(0))
assert(Calculator.+(Fail("Badness"), 1) == Fail("Badness"))
assert(Calculator./(Succeed(4), 0) == Fail("Division by zero"))
assert(Calculator./(Fail("Badness"), 0) == Fail("Badness"))

Calculator./(Calculator.+(Calculator.*(Succeed(4), 3), 4), 5)
