sealed trait CalculationResult
final case class Succeed(result: Int) extends CalculationResult
final case class Fail(reason: String) extends CalculationResult

object Calculator {
  def add(a: Int, b: Int): CalculationResult = Succeed(a + b)
  def sub(a: Int, b: Int): CalculationResult = Succeed(a - b)
  def mul(a: Int, b: Int): CalculationResult = Succeed(a * b)
  def div(a: Int, b: Int): CalculationResult = if (b == 0) Fail("Division by zero") else Succeed(a / b)
}

Calculator.add(1, 2)
Calculator.sub(10, 7)
Calculator.mul(2, 4)
Calculator.div(21, 7)
Calculator.div(11, 0)