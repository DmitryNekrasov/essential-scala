import scala.math.abs

implicit val absOrdering: Ordering[Int] = Ordering.fromLessThan((a, b) => abs(a) < abs(b))

assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))
assert(List(-4, -3, -2, -1).sorted == List(-1, -2, -3, -4))

final case class Rational(num: Int, den: Int)

object Rational {
  implicit val rationalOrdering: Ordering[Rational] =
    Ordering.fromLessThan((x, y) => x.num * y.den < y.num * x.den)
}

assert(List(Rational(1, 2), Rational(3, 4), Rational(1, 3)).sorted ==
  List(Rational(1, 3), Rational(1, 2), Rational(3, 4)))

final case class Order(units: Int, unitPrice: Double) {
  val totalPrice = units * unitPrice
}

object Order {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan(_.totalPrice < _.totalPrice)
}

object OrderingByUnits {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan(_.units < _.units)
}

object OrderingByUnitPrice {
  implicit val ordering: Ordering[Order] = Ordering.fromLessThan(_.unitPrice < _.unitPrice)
}

List(Order(1, 100), Order(5, 21), Order(3, 50)).sorted