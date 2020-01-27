case class Person(name: String, age: Int)

trait HtmlWriter[A] {
  def write(in: A): String
}

implicit class HtmlOps[A](data: A) {
  def toHtml(implicit writer: HtmlWriter[A]) = writer.write(data)
}

object HtmlWriter {
  def apply[A](implicit writer: HtmlWriter[A]): HtmlWriter[A] = writer
}

object HtmlUtil {
  def htmlify[A](data: A)(implicit writer: HtmlWriter[A]): String = writer.write(data)

  def htmlify2[A : HtmlWriter](data: A): String = data.toHtml
}

implicit case object PersonWriter extends HtmlWriter[Person] {
  def write(person: Person) = s"<span> ${person.name} &lt; ${person.age} &gt </span>"
}

val person = Person("John", 21)

HtmlUtil.htmlify(person)
HtmlWriter[Person].write(person)
person.toHtml

implicitly[HtmlWriter[Person]]