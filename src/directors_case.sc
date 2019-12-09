case class Director(firstName: String, lastName: String, yearOfBirth: Int) {
  def name = s"$firstName $lastName"
}

object Director {
  def older(d1: Director, d2: Director) = if (d1.yearOfBirth > d2.yearOfBirth) d1 else d2
}

case class Film(name: String, yearOfRelease: Int, imdbRating: Double, director: Director) {
  def directorsAge = yearOfRelease - director.yearOfBirth
  def isDirectedBy(director: Director) = this.director == director
}

object Film {
  def highestRating(film1: Film, film2: Film) =
    film1.imdbRating max film2.imdbRating

  def oldestDirectorAtTheTime(film1: Film, film2: Film) =
    if (film1.directorsAge > film2.directorsAge) film1.director else film2.director
}

object Dad {
  def rate(film: Film) =
    film match {
      case Film(_, _, _, Director("Clint", "Eastwood", _)) => 10.0
      case Film(_, _, _, Director("John", "McTiernan", _)) => 7.0
      case Film(_, _, _, _) => 3.0
    }
}

val clintFilm = Film("Clint film name", 1976, 8.8, Director("Clint", "Eastwood", 1900))
val johnFilm = Film("John film name", 1978, 9.1, Director("John", "McTiernan", 1901))
val anotherFilm = Film("Another film name", 1980, 7.1, Director("Unknown", "Director", 1902))

Dad rate clintFilm
Dad rate johnFilm
Dad rate anotherFilm