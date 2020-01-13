import scala.math.Pi

sealed trait Color {
  def red: Int
  def green: Int
  def blue: Int

  final def isLight = (red + green + blue) / 3 > 128
  final def isDark = !isLight
}

object Red extends Color {
  val red = 255
  val green = 0
  val blue = 0
}

object Yellow extends Color {
  val red = 255
  val green = 255
  val blue = 0
}

object Pink extends Color {
  val red = 255
  val green = 192
  val blue = 203
}

final case class CustomColor(red: Int, green: Int, blue: Int) extends Color

sealed trait Shape {
  def sides: Int
  def perimeter: Double
  def area: Double
  def color: Color

  def stats: String = s"sides: $sides, perimeter: $perimeter, area: $area"
}

sealed trait Rectangular extends Shape {
  def width: Double
  def height: Double

  val sides = 4
  val perimeter = (width + height) * 2
  val area = width * height
}

final case class Circle(radius: Double, color: Color) extends Shape {
  val sides = 1
  val perimeter = 2 * Pi * radius
  val area = Pi * radius * radius
}

final case class Rectangle(width: Double, height: Double, color: Color) extends Rectangular

final case class Square(size: Double, color: Color) extends Rectangular {
  def width = size
  def height = size
}

object Draw {
  def apply(shape: Shape): String =
    shape match {
      case Circle(radius, color) => s"A ${Draw(color)} circle of radius ${radius}cm"
      case Rectangle(width, height, color) => s"A ${Draw(color)} rectangle of width ${width}cm and height ${height}cm"
      case Square(size, color) => s"A ${Draw(color)} square of size ${size}cm"
    }

  def apply(color: Color): String =
    color match {
      case Red => "red"
      case Yellow => "yellow"
      case Pink => "pink"
      case color => if (color.isLight) "light" else "dark"
    }
}

val circle = Circle(5, Red)
circle.stats

val rectangle = Rectangle(3, 5, Pink)
rectangle.stats

val square = Square(7, CustomColor(255, 255, 255))
square.stats

Draw(circle)
Draw(rectangle)
Draw(square)
