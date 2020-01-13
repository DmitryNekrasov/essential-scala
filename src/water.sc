sealed trait Source
case object Well extends Source
case object Spring extends Source
case object Tap extends Source

final case class Water(size: Int, source: Source, carbonated: Boolean)

val water = Water(3, Well, carbonated = true)