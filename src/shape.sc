import scala.math.Pi

trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double

  def stats: String = s"sides: $sides, perimeter: $perimeter, area: $area"
}

case class Circle(radius: Double) extends Shape {
  val sides = 1
  val perimeter = 2 * Pi * radius
  val area = Pi * radius * radius
}

case class Rectangle(width: Double, height: Double) extends Shape {
  val sides = 4
  val perimeter = (width + height) * 2
  val area = width * height
}

case class Square(size: Double) extends Shape {
  val sides = 4
  val perimeter = size * 4
  val area = size * size
}

val circle = Circle(5)
circle.stats

val rectangle = Rectangle(3, 5)
rectangle.stats

val square = Square(7)
square.stats