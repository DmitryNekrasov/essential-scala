object SimpleCalculator extends App {
  def calculator(operand1: String, operator: String, operand2: String): Option[Int] =
    for {
      first <- operand1.toIntOption
      second <- operand2.toIntOption
      f <- operation(operator)
      result <- f(first, second)
    } yield result

  def calculatorMap(operand1: String, operator: String, operand2: String): Option[Int] =
    operand1.toIntOption.flatMap { first =>
      operand2.toIntOption.flatMap { second =>
        operation(operator).flatMap { f =>
          f(first, second)
        }
      }
    }

  def operation(operator: String): Option[(Int, Int) => Option[Int]] =
    operator match {
      case "+" => Some((a, b) => Some(a + b))
      case "-" => Some((a, b) => Some(a - b))
      case "*" => Some((a, b) => Some(a * b))
      case "/" => Some((num, den) => if (den != 0) Some(num / den) else None)
      case _ => None
    }

  val v1 = calculator("15", "+", "100")
  println(v1)

  def doAsserts(f: (String, String, String) => Option[Int]): Unit = {
    assert(f("15", "+", "100").contains(115))
    assert(f("50", "-", "28").contains(22))
    assert(f("7", "*", "20").contains(140))
    assert(f("1305", "/", "15").contains(87))
    assert(f("1305", "/", "0").isEmpty)
    assert(f("15", "_", "100").isEmpty)
    assert(f("aaa", "+", "100").isEmpty)
    assert(f("15", "+", "qqq").isEmpty)
  }

  doAsserts(calculator)
  doAsserts(calculatorMap)
}
