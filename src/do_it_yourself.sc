import scala.math.min

def smallest(seq: Seq[Int]): Int =
  seq.foldLeft(Int.MaxValue)(min)

def unique(seq: Seq[Int]): Seq[Int] =
  seq.foldLeft(Seq.empty[Int])((s, value) => if (s.contains(value)) s else s :+ value)

def foldLeft[A, B](seq: Seq[A])(empty: B)(f: (B, A) => B): B = {
  var start = empty
  seq.foreach(elem => start = f(start, elem))
  start
}

def reverse[A](seq: Seq[A]): Seq[A] =
  foldLeft(seq)(Seq.empty[A])((seq, value) => value +: seq)

def map[A, B](seq: Seq[A], f: A => B): Seq[B] =
  seq.foldRight(Seq.empty[B])((value, seq) => f(value) +: seq)

val seq = Seq(2, 5, 6, 3, 4, 1, 8, 1, 3, 4, 5, 2, 3, 4, 1, 5, 4, 2, 3)
smallest(seq)
unique(unique(seq))
reverse(seq)

map[Int, Double](seq, _ + 0.1)