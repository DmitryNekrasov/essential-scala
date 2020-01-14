import scala.annotation.tailrec

object IntListExample extends App {
  sealed trait IntList {
    final def length: Int = length(0)

    @tailrec
    private def length(accum: Int): Int =
      this match {
        case End => accum
        case Pair(_, tail) => tail.length(accum + 1)
      }
  }
  case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

  val list = Pair(1, Pair(2, Pair(3, End)))

  assert(list.length == 3)
  assert(list.tail.length == 2)
  assert(End.length == 0)

  println(list.length)
}
