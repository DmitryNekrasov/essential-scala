case class Person(val firstName: String, val lastName: String) {
  def fullName = s"$firstName $lastName"
}

object Person {
  def apply(fullName: String): Person = {
    val parts = fullName split " "
    new Person(parts(0), parts(1))
  }
}

val p1 = Person("John", "Lennon")
p1.fullName

val p2 = Person("Paul McCartney")
p2.fullName