object Generics extends App {
  final case class Pair[A, B](one: A, two: B)

  sealed trait Sum[A, B] {
    final def fold[C](left: A => C)(right: B => C): C =
      this match {
        case Left(value) => left(value)
        case Right(value) => right(value)
      }
  }
  final case class Left[A, B](value: A) extends Sum[A, B]
  final case class Right[A, B](value: B) extends Sum[A, B]

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

  val l1 = Left[Int, String](1).value
  println(l1)

  val r1 = Right[Int, String]("foo").value
  println(r1)

  val sum: Sum[Int, String] = Right("foo")

  val result = sum match {
    case Left(value) => value.toString
    case Right(value) => value
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
