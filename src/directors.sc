class Director(
                val firstName: String,
                val lastName: String,
                val yearOfBirth: Int
              ) {
  def name = s"$firstName $lastName"
}

object Director {
  def apply(
             firstName: String,
             lastName: String,
             yearOfBirth: Int
           ) =
    new Director(firstName, lastName, yearOfBirth)

  def older(d1: Director, d2: Director) = if (d1.yearOfBirth > d2.yearOfBirth) d1 else d2
}

class Film(
            val name: String,
            val yearOfRelease: Int,
            val imdbRating: Double,
            val director: Director
          ) {
  def directorsAge = yearOfRelease - director.yearOfBirth

  def isDirectedBy(director: Director) = this.director == director

  def copy(
            name: String = name,
            yearOfRelease: Int = yearOfRelease,
            imdbRating: Double = imdbRating,
            director: Director = director
          ) =
    new Film(name, yearOfRelease, imdbRating, director)
}

object Film {
  def apply(
             name: String,
             yearOfRelease: Int,
             imdbRating: Double,
             director: Director
           ) =
    new Film(name, yearOfRelease, imdbRating, director)

  def highestRating(film1: Film, film2: Film) =
    film1.imdbRating max film2.imdbRating

  def oldestDirectorAtTheTime(film1: Film, film2: Film) =
    if (film1.directorsAge > film2.directorsAge) film1.director else film2.director
}

def printFilm(film: Film): Unit = println(s"${film.name}\n${film.yearOfRelease}\n${film.imdbRating}\n${film.director.name}")

val nolan = new Director("Christopher", "Nolan", 1970)
val inception = new Film("Inception", 2010, 8.8, nolan)

inception.directorsAge

val filmCopy = inception.copy(yearOfRelease = 1984).copy().copy(name = "New Name")

printFilm(inception)
printFilm(filmCopy)