import scala.util.Try

val opt1 = Some(7)
val opt2 = Some(3)
val opt3 = Some(5)

for {
  a <- opt1
  b <- opt2
  c <- opt3
} yield a + b + c

val seq1 = Seq(1)
val seq2 = Seq(2)
val seq3 = Seq(3)

for {
  a <- seq1
  b <- seq2
  c <- seq3
} yield a + b + c

val try1 = Try(1)
val try2 = Try(2)
val try3 = Try(3)

for {
  a <- try1
  b <- try2
  c <- try3
} yield a + b + c

val seqOfSeq = Seq(Seq(1, 2), Seq(3, 4, 5), Seq(6), Seq(7), Seq())
for {
  seq <- seqOfSeq
  elem <- seq
} yield elem