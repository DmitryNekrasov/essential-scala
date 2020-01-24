object Distribution extends App {

  final case class Distribution[A](events: List[(A, Double)]) {
    def map[B](f: A => B): Distribution[B] =
      Distribution(events.map { case (a, p) => f(a) -> p })

    def flatMap[B](f: A => Distribution[B]): Distribution[B] =
      Distribution(events.flatMap { case (a, p1) => f(a).events.map { case (b, p2) => b -> p1 * p2 } }).compact.normalize

    def normalize: Distribution[A] = {
      val totalP = events.map { case (_, p) => p }.sum
      Distribution(events.map { case (a, p) => a -> p / totalP })
    }

    def compact: Distribution[A] = {
      def prob(a: A): Double =
        events.filter { case (x, _) => x == a }.map { case (_, p) => p }.sum

      val distinct = events.map { case (a, _) => a }.distinct
      Distribution(distinct.map(a => a -> prob(a)))
    }
  }

  object Distribution {
    def uniform[A](list: List[A]): Distribution[A] = {
      val p = 1.0 / list.size
      Distribution(list.map(_ -> p))
    }
  }

  sealed trait Coin
  final case object Heads extends Coin
  final case object Tails extends Coin

  val fairCoin = Distribution.uniform(List(Heads, Tails))

  val threeFlips = for {
    c1 <- fairCoin
    c2 <- fairCoin
    c3 <- fairCoin
  } yield (c1, c2, c3)

  println(threeFlips)

  val threeFlipsFlatMap = fairCoin.flatMap { c1 =>
    fairCoin.flatMap { c2 =>
      fairCoin.map { c3 =>
        (c1, c2, c3)
      }
    }
  }

  println(threeFlipsFlatMap)

  val twoFlip = fairCoin.flatMap { c1 =>
    fairCoin.map(c2 => (c1, c2))
  }

  println(twoFlip)
}
