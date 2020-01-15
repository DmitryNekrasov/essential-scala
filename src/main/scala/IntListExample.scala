import scala.annotation.tailrec

object IntListExample extends App {
  sealed trait IntList {
    final def length: Int = fold[Int](0, (_, accum) => 1 + accum)
    final def product: Int = fold[Int](1, (head, accum) => head * accum)
    final def sum : Int = fold[Int](0, (head, accum) => head + accum)
    final def double: IntList = fold[IntList](End, (head, accum) => Pair(head * 2, accum))

    final def asString: String =
      this match {
        case End => ""
        case Pair(head, End) => head.toString
        case Pair(head, tail) => tail.asString(head.toString)
      }

    @tailrec
    private def asString(accum: String): String =
      this match {
        case End => accum
        case Pair(head, tail) => tail.asString(s"$accum, $head")
      }

    private def fold[A](end: A, f: (Int, A) => A): A =
      this match {
        case End => end
        case Pair(head, tail) => f(head, tail.fold(end, f))
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
