object Positive {
  def unapply(value: Int): Option[Int] = if (value > 0) Some(value) else None
}

assert((0 match {
  case Positive(_) => "Yes"
  case _ => "No"
}) == "No")

assert((42 match {
  case Positive(_) => "Yes"
  case _ => "No"
}) == "Yes")

object Titlecase {
  def unapply(str: String): Option[String] =
    Some(str.split(" ").map {
      case "" => ""
      case str => str.substring(0, 1).toUpperCase + str.substring(1)
    }.mkString(" "))
}

val str = "hello"
str.substring(0, 1).toUpperCase + str.substring(1, str.length)

assert(("sir lord doctor david gurnell" match {
  case Titlecase(str) => str
}) == "Sir Lord Doctor David Gurnell")