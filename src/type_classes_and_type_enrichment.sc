object IntImplicits {
  implicit class ExtraIntMethods(value: Int) {
    def yeah(): Unit = for (_ <- 0 until value) println("Oh yeah!")

    def times(f: Int => Unit): Unit = for (i <- 0 until value) f(i)
  }
}

import IntImplicits._

1.yeah()
3.yeah()
(-1).yeah()

8.times(n => println(s"Look - it's the number $n"))