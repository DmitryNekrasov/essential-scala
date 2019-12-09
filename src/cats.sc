case class Cat(name: String, colour: String, food: String)

object ChipShop {
  def willServe(cat: Cat) =
    cat match {
      case Cat(_, _, "Chips") => true
      case Cat(_, _, _) => false
    }
}

val oswald = Cat("Oswald", "Black", "Milk")
val henderson = Cat("Henderson", "Ginger", "Chips")
val quentin = Cat("Quentin", "Tabby and white", "Curry")

ChipShop.willServe(oswald)
ChipShop.willServe(henderson)
ChipShop willServe quentin