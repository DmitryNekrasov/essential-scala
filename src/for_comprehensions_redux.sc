for {
  x <- Seq(1, 2, 3, 4, 5, 6, 7) if x % 2 == 1
} yield x * x

Seq(1, 2, 3) zip Seq(4, 5, 6)

for {
  (a, b) <- Seq(1, 2, 3) zip Seq(4, 5, 6)
} yield a * 10 + b

for {
  (value, index) <- Seq(73, 12, 47, 80, 32).zipWithIndex
} println(s"value: $value, index: $index")

for((a, b) <- Seq(1, 2, 3) zip Seq(4, 5, 6)) yield a + b