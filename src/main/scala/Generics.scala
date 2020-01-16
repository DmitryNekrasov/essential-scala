object Generics extends App {
  final case class Pair[A, B](one: A, two: B)

  sealed trait Sum[A, B] {
    final def fold[C](failure: A => C)(success: B => C): C =
      this match {
        case Failure(a) => failure(a)
        case Success(b) => success(b)
      }

    final def map[C](f: B => C) : Sum[A, C] = flatMap[C](value => Success(f(value)))

    final def flatMap[C](f: B => Sum[A, C]): Sum[A, C] =
      this match {
        case Failure(a) => Failure(a)
        case Success(b) => f(b)
      }
  }
  final case class Failure[A, B](value: A) extends Sum[A, B]
  final case class Success[A, B](value: B) extends Sum[A, B]

  sealed trait Maybe[A] {
    final def fold[B](empty: B)(full: A => B): B =
      this match {
        case Empty() => empty
        case Full(value) => full(value)
      }

    final def map[B](f: A => B): Maybe[B] = flatMap[B](value => Full(f(value)))

    final def flatMap[B](f: A => Maybe[B]): Maybe[B] =
      this match {
        case Empty() => Empty[B]()
        case Full(value) => f(value)
      }
  }
  final case class Full[A](value: A) extends Maybe[A]
  final case class Empty[A]() extends Maybe[A]

  val pair = Pair("hi", 3)

  println(pair)
  println(pair.one)
  println(pair.two)

  val l1 = Failure[Int, String](1).value
  println(l1)

  val r1 = Success[Int, String]("foo").value
  println(r1)

  val sum: Sum[Int, String] = Success("foo")

  val result = sum match {
    case Failure(value) => value.toString
    case Success(value) => value
  }

  println(result)

  val perhaps1: Maybe[Int] = Empty[Int]()
  val perhaps2: Maybe[Int] = Full(1)
  println(perhaps1, perhaps2)

  val list1 = List(Full(3), Full(2), Full(1)).map(maybe => if (maybe.value % 2 == 1) Empty() else maybe)
  println(list1)

  val list2 = List(Full(3), Full(2), Full(1)).map(maybe => maybe.flatMap[Int](x => if (x % 2 == 1) Empty() else Full(x)))
  println(list2)
}
