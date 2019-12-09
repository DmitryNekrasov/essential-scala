class Adder(amount: Int) {
  def apply(in: Int) = in + amount
}

case class Counter(count: Int = 0) {
  def inc: Counter = inc()
  def dec: Counter = dec()
  def inc(x: Int = 1) = copy(count = count + x)
  def dec(x: Int = 1) = copy(count = count - x)

  def adjust(adder: Adder) = new Counter(adder(count))
}

val counter = Counter(10)
counter.inc(3).dec(5).inc.dec(4).inc.dec.count

val counter2 = Counter(10)
val counter3 = counter2.adjust(new Adder(5))
counter3.count

val adder = new Adder(100)
val result = adder(7)

val c1 = Counter(5)
c1.inc.inc.dec.inc == Counter(7)