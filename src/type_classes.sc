trait Equal[A] {
  def equal(x: A, y: A): Boolean
}

object Equal {
  def apply[A](implicit instance: Equal[A]): Equal[A] = instance
}

object Eq {
  def apply[A](first: A, second: A)(implicit equal: Equal[A]): Boolean =
    equal.equal(first, second)
}

case class Person(name: String, email: String)

object EqualPersonsByEmailImplicit {
  implicit object EqualPersonsByEmail extends Equal[Person] {
    def equal(x: Person, y: Person): Boolean =
      x.email == y.email
  }
}

object EqualPersonsByNameAndEmailImplicit {
  implicit object EqualPersonsByNameAndEmail extends Equal[Person] {
    def equal(x: Person, y: Person): Boolean =
      x.name == y.name && x.email == y.email
  }
}

import EqualPersonsByNameAndEmailImplicit._

Equal[Person].equal(Person("Noel1", "noel@example.com"), Person("Noel1", "noel@example.com"))