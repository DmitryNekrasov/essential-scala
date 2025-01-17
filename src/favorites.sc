val people = Set("Alice", "Bob", "Charlie", "Derek", "Edith", "Fred")
val ages = Map( "Alice" -> 20, "Bob" -> 30, "Charlie" -> 50, "Derek" -> 40, "Edith" -> 10, "Fred" -> 60)
val favoriteColors = Map( "Bob" -> "green", "Derek" -> "magenta", "Fred" -> "yellow")
val favoriteCats = Map( "Alice" -> "Long Cat", "Charlie" -> "Ceiling Cat", "Edith" -> "Cloud Cat")

def favoriteColor(name: String): String =
  favoriteColors.getOrElse(name, "beige")

def printColors(): Unit =
  for (name <- people) println(s"$name like ${favoriteColor(name)}")

def lookup[A](name: String, map: Map[String, A]): Option[A] =
  map.get(name)

favoriteColor("Fred")
favoriteColor("Alice")

printColors()

lookup("Bob", ages)
lookup("Bob", favoriteColors)
lookup("Bob", favoriteCats)

val (name, _) = ages.maxBy { case (_, age) => age }
favoriteColor(name)