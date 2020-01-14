import scala.annotation.tailrec

object IntListExample extends App {
  sealed trait IntList {
    final def length: Int = length(0)
    final def product: Int = product(1)

    final def double: IntList =
      this match {
        case End => End
        case Pair(head, tail) => Pair(head * 2, tail.double)
      }

    final def asString: String =
      this match {
        case End => ""
        case Pair(head, End) => head.toString
        case Pair(head, tail) => tail.asString(head.toString)
      }

    @tailrec
    private def length(accum: Int): Int =
      this match {
        case End => accum
        case Pair(_, tail) => tail.length(accum + 1)
      }

    @tailrec
    private def product(accum: Int): Int =
      this match {
        case End => accum
        case Pair(head, tail) => tail.product(accum * head)
      }

    @tailrec
    private def asString(accum: String): String =
      this match {
        case End => accum
        case Pair(head, tail) => tail.asString(s"$accum, $head")
      }
  }
  case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

  val list = Pair(1, Pair(2, Pair(3, End)))

  println(list.asString)
  println(s"length = ${list.length}")
  println(s"product = ${list.product}")
  println(list.double.asString)

  assert(list.length == 3)
  assert(list.tail.length == 2)
  assert(End.length == 0)

  assert(list.product == 6)
  assert(list.tail.product == 6)
  assert(End.product == 1)

  assert(list.double == Pair(2, Pair(4, Pair(6, End))))
  assert(list.tail.double == Pair(4, Pair(6, End)))
  assert(End.double == End)
}
