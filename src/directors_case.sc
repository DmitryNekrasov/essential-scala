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