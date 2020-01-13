import scala.math.Pi

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double

  def stats: String = s"sides: $sides, perimeter: $perimeter, area: $area"
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double

  val sides = 4
  val perimeter = (width + height) * 2
  val area = width * height
}

final case class Circle(radius: Double) extends Shape {
  val sides = 1
  val perimeter = 2 * Pi * radius
  val area = Pi * radius * radius
}

final case class Rectangle(width: Double, height: Double) extends Rectangular

final case class Square(size: Double) extends Rectangular {
  def width = size
  def height = size
}

object Draw {
  def apply(shape: Shape): String =
    shape match {
      case Circle(radius) => s"A circle of radius ${radius}cm"
      case Rectangle(width, height) => s"A rectangle of width ${width}cm and height ${height}cm"
      case Square(size) => s"A square of size ${size}cm"
    }
}

val circle = Circle(5)
circle.stats

val rectangle = Rectangle(3, 5)
rectangle.stats

val square = Square(7)
square.stats

Draw(circle)
Draw(rectangle)
Draw(square)
