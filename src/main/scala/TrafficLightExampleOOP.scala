object TrafficLightExampleOOP extends App {
  sealed trait TrafficLight {
    def next: TrafficLight
  }

  case object Red extends TrafficLight {
    val next: TrafficLight = Green
  }

  case object Green extends TrafficLight {
    val next: TrafficLight = Yellow
  }

  case object Yellow extends TrafficLight {
    val next: TrafficLight = Red
  }

  println(Red.next)
  println(Red.next.next)
  println(Red.next.next.next)
}
