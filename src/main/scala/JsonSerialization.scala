import java.util.Date

object JsonSerialization extends App {

  sealed trait Visitor {
    def id: String

    def createdAt: Date

    final def age: Long = new Date().getTime - createdAt.getTime
  }

  final case class Anonymous(
                              id: String,
                              createdAt: Date = new Date()
                            ) extends Visitor

  final case class User(
                         id: String,
                         email: String,
                         createdAt: Date = new Date()
                       ) extends Visitor

  sealed trait JsValue {
    def stringify: String
  }

  final case class JsObject(values: Map[String, JsValue]) extends JsValue {
    def stringify: String =
      values.map { case (key, value) => s"""\"$key\": ${value.stringify}""" }.mkString("{", ", ", "}")
  }

  final case class JsString(value: String) extends JsValue {
    def stringify: String = s"""\"$value\""""
  }

  trait JsWriter[A] {
    def write(value: A): JsValue
  }

  implicit class JsUtil[A](value: A) {
    def toJson(implicit writer: JsWriter[A]): JsValue = writer.write(value)
  }

  implicit object StringWriter extends JsWriter[String] {
    def write(str: String): JsValue = JsString(str)
  }

  implicit object DateWriter extends JsWriter[Date] {
    def write(date: Date): JsValue = JsString(date.toString)
  }

  implicit final case object AnonymousWriter extends JsWriter[Anonymous] {
    def write(anonymous: Anonymous): JsValue =
      JsObject(Map(
        "id" -> anonymous.id.toJson,
        "createdAt" -> anonymous.createdAt.toJson
      ))
  }

  implicit final case object UserWriter extends JsWriter[User] {
    def write(user: User): JsValue =
      JsObject(Map(
        "id" -> user.id.toJson,
        "email" -> user.email.toJson,
        "createdAt" -> user.createdAt.toJson
      ))
  }

  implicit final case object VisitorWriter extends JsWriter[Visitor] {
    def write(visitor: Visitor): JsValue =
      visitor match {
        case anonymous: Anonymous => anonymous.toJson
        case user: User => user.toJson
      }
  }

  val visitors: Seq[Visitor] = Seq(
    Anonymous("001"),
    User("003", "dave@example.com")
  )

  visitors.map(_.toJson).map(_.stringify).foreach(println)
}
