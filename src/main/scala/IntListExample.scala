import scala.annotation.tailrec

object IntListExample extends App {
  sealed trait IntList {
    final def length: Int = length(0)
    final def product: Int = product(1)

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
  }
  case object End extends IntList
  final case class Pair(head: Int, tail: IntList) extends IntList

  val list = Pair(1, Pair(2, Pair(3, End)))

  assert(list.length == 3)
  assert(list.tail.length == 2)
  assert(End.length == 0)

  assert(list.product == 6)
  assert(list.tail.product == 6)
  assert(End.product == 1)

  println(list.length)
  println(list.product)
}
