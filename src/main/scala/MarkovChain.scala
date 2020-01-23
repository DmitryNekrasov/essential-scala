object MarkovChain extends App {
  val subjects = List("Noel", "The cat", "The dog")
  val verbs = List("wrote", "chased", "slept on")
  val objects = List("the book", "the ball", "the bed")

  val sentences = for {
    subject <- subjects
    verb <- verbs
    obj <- objects
  } yield (subject, verb, obj)

  println(sentences.mkString("\n"))
}
