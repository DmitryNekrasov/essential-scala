object Calculator extends App {
  sealed trait Expression {
    final def eval: Double =
      this match {
        case Number(value) => value
        case Addition(left, right) => left.eval + right.eval
        case Subtraction(left, right) => left.eval - right.eval
      }
  }
  final case class Addition(left: Expression, right: Expression) extends Expression
  final case class Subtraction(left: Expression, right: Expression) extends Expression
  final case class Number(value: Double) extends Expression

  val expr = Addition(Number(5), Subtraction(Addition(Number(4), Number(6)), Number(3)))
  println(expr.eval)
}
