trait Feline {
  def colour: String
  def sound: String
}

trait BigCat extends Feline {
  override val sound = "roar"
}

case class Cat(colour: String, sound: String = "meow", favouriteFood: String) extends Feline

case class Tiger(colour: String) extends BigCat

case class Lion(colour: String, maneSize: Int) extends BigCat

case class Panther(colour: String) extends BigCat

val cat = Cat(colour = "red", favouriteFood = "wurst")
val tiger = Tiger("ginger")
val lion = Lion("blue", 5)
val panther = Panther("black")

tiger.sound