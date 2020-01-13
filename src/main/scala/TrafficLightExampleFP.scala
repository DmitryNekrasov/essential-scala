object TrafficLightExampleFP extends App {
  sealed trait TrafficLight {
    final def next: TrafficLight =
      this match {
        case Red => Green
        case Green => Yellow
        case Yellow => Red
      }
  }

  case object Red extends TrafficLight
  case object Green extends TrafficLight
  case object Yellow extends TrafficLight

  println(Red.next)
  println(Red.next.next)
  println(Red.next.next.next)
}
