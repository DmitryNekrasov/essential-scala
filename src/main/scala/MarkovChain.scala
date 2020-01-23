object MarkovChain extends App {
  final case class Distribution[A](events: List[(A, Double)]) {
    def flatMap[B](f: A => Distribution[B]): Distribution[B] =
      ???

    def map[B](f: A => B): Distribution[B] =
      Distribution(events.map { case (a, p) => f(a) -> p })
  }

  def uniform[A](list: List[A]): Distribution[A] = {
    val p = 1.0 / list.size
    Distribution(list.map(_ -> p))
  }

  def verbsFor(subject: String): List[String] =
    subject match {
      case "Noel" => List("wrote", "chased", "slept on")
      case "The cat" => List("meowed at", "chased", "slept on")
      case "The dog" => List("barked at", "chased", "slept on")
    }

  def objectsFor(verb: String): List[String] =
    verb match {
      case "wrote" => List("the book", "the letter", "the code")
      case "chased" => List("the ball", "the dog", "the cat")
      case "slept on" => List("the bed", "the mat", "the train")
      case "meowed at" => List("Noel", "the door", "the food cupboard")
      case "barked at" => List("the postman", "the car", "the cat")
    }

  val subjects = List("Noel", "The cat", "The dog")

  val sentences = for {
    subject <- subjects
    verb <- verbsFor(subject)
    obj <- objectsFor(verb)
  } yield (subject, verb, obj)

  println(sentences.mkString("\n"))
}