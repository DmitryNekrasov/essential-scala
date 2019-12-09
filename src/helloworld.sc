val sum3: Int => Int => Int => Int = x1 => x2 => x3 => x1 * 100 + x2 * 10 + x3

def sum3v2(x1: Int)(x2: Int)(x3: Int): Int = { x1 * 100 + x2 * 10 + x3 }

val sum3v3 = (x1: Int) => (x2: Int) => (x3: Int) => x1 * 100 + x2 * 10 + x3

val mag: Int => Int => (Int, Double) => Double =
  x1 => x2 => (x3, y) => x1 * 100 + x2 * 10 + x3 + y / 10

def genericSum3[T]: T => T => T => String = v1 => v2 => v3 => s"$v1 $v2 $v3"

val mad1: (Int => Int) => (Int => Int) = f => x => f(x) * 10

val mad2: Int => (Int => Int) => Int = x => f => f(x) * 10

val mad3 = (x: Int) => (f: Int => Int) => f(x) * 10

sum3(1)(2)(3)

sum3v2(1)(2)(3)

sum3v3(1)(2)(3)

mag(1)(2)(3, 4)

genericSum3("aaa")("bbb")("ccc")
genericSum3(1)(2)(3)

mad1(x => x * x)(5)
mad2(7)(x => x * x)
mad3(3)(x => x * x)

1.+(5)

def f1(x: Int, y: Int): Int = { x + y }
def f2(x: Int)(y: Int): Int = { x + y }
val f3: Int => Int => Int = x => y => x + y
val f4 = (x: Int) => (y: Int) => x + y
val f5: Int => Int = x => x * x

f1(3, 5)
f2(3)(5)
f3(3)(5)
f4(3)(5)

f5(7)