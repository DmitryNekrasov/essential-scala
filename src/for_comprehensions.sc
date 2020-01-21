val seq = Seq(1, 2, 3)

seq.map(_ * 2)

for {
  x <- seq
} yield x * 2

val data = Seq(Seq(1, 2), Seq(3, 4, 5), Seq(6))

data.flatMap(_.map(_ * 2))

for {
  subseq <- data
  elem <- subseq
} yield elem * 2

for(x <- Seq(1, 2, 3)) yield x * x