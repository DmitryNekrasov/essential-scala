import scala.math.Pi

trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double

  def stats: String = s"sides: $sides, perimeter: $perimeter, area: $area"
}

trait Rectangular extends Shape {
  def width: Double
  def height: Double

  override val sides = 4
  override val perimeter = (width + height) * 2
  override val area = width * height
}

case class Circle(radius: Double) extends Shape {
  val sides = 1
  val perimeter = 2 * Pi * radius
  val area = Pi * radius * radius
}

case class Rectangle(width: Double, height: Double) extends Rectangular

case class Square(size: Double) extends Rectangular {
  override def width = size
  override def height = size
}

val circle = Circle(5)
circle.stats

val rectangle = Rectangle(3, 5)
rectangle.stats

val square = Square(7)
square.stats
