import scala.collection.immutable._

val seq = Seq(1, 2, 3, 1, 5, 2, 5)
seq.tail
seq.length
seq.contains(3)
val op = seq.find(_ == 5)
seq.filter(_ >= 3)
seq.sortWith(_ < _)
10 +: seq :+ 20
seq ++ Seq(10, 20, 30)

val list = 11 :: 22 :: 33 :: Nil ::: 111 :: Nil

val vec = Vector(1, 2, 3)

val str = List(1, 2, 3, 4, 5).mkString("[", ", ", "]")