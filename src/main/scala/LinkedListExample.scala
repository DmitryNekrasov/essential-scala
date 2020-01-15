import scala.annotation.tailrec

object LinkedListExample extends App {
  sealed trait LinkedList[A] {
    final def length: Int =
      this match {
        case Pair(_, tail) => 1 + tail.length
        case End() => 0
      }

    @tailrec
    final def contains(value: A): Boolean =
      this match {
        case Pair(head, tail) => head == value || tail.contains(value)
        case End() => false
      }

    final def apply(index: Int): Result[A] =
      this match {
        case Pair(head, tail) => if (index == 0) Success(head) else tail(index - 1)
        case End() => Failure("Index out of bounds")
      }
  }
  final case class Pair[A](head: A, tail: LinkedList[A]) extends LinkedList[A]
  final case class End[A]() extends LinkedList[A]

  sealed trait Result[A]
  final case class Success[A](value: A) extends Result[A]
  final case class Failure[A](reason: String) extends Result[A]

  val list = Pair(1, Pair(2, Pair(3, End())))
  println(list.length)

  assert(list.length == 3)
  assert(list.tail.length == 2)
  assert(End().length == 0)

  assert(list.contains(3))
  assert(!list.contains(4))
  assert(!End().contains(0))

  assert(list(0) == Success(1))
  assert(list(1) == Success(2))
  assert(list(2) == Success(3))
  assert(list(3) == Failure("Index out of bounds"))
}
