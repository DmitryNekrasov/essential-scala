val seq1 = Seq(Some(1), None, Some(5), None, Some(7))
seq1.flatMap(x => x)
seq1.flatten

val seq2 = Seq(Seq(1), Seq(), Seq(5), Seq(), Seq(7))
seq2.flatMap(x => x)

val seq3 = Seq(1, 2, 3)
seq3.flatMap(x => Seq(x))
seq3.flatMap(x => Some(x))

val optionA = Some(1)
val optionB = Some(2)

for {
  a <- optionA
  b <- optionB
} yield a + b

optionA.flatMap { a =>
  optionB.map { b =>
    a + b
  }
}