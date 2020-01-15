final case class Box[A](value: A)

val b1 = Box(1)
val b2 = Box("Hello")
val b3 = Box(Box("World"))
val b4 = b3.value