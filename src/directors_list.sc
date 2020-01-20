case class Film(name: String, yearOfRelease: Int, imdbRating: Double)

case class Director(firstName: String, lastName: String, yearOfBirth: Int, films: Seq[Film])

object Calc {
  def directorsWithBackCatalogOfSize(directors: Seq[Director], numberOfFilms: Int): Seq[Director] =
    directors.filter(_.films.size > numberOfFilms)

  def directorBornBefore(directors: Seq[Director], year: Int): Seq[Director] =
    directors.filter(_.yearOfBirth < year)

  def directorBornBeforeWithBackCatalogOfSize(directors: Seq[Director], year: Int, numberOfFilms: Int): Seq[Director] =
    directorsWithBackCatalogOfSize(directorBornBefore(directors, year), numberOfFilms)

  def sort(directors: Seq[Director], specified: Boolean = true): Seq[Director] =
    if (specified)
      directors.sortWith(_.yearOfBirth < _.yearOfBirth)
    else
      directors.sortWith(_.yearOfBirth > _.yearOfBirth)
}

val memento = Film("Memento", 2000, 8.5)
val darkKnight = Film("Drak Knight", 2008, 9.0)
val inception = Film("Inception", 2010, 8.8)

val highPlainsDrifter = Film("High Plains Drifter", 1973, 7.7)
val outlawJoseyWales = Film("The Outlaw Josey Wales", 1976, 7.9)
val unforgiven = Film("Unforgiven", 1992, 8.3)
val granTorino = Film("Gran Torino", 1008, 8.2)
val invictus = Film("Invictus", 2009, 7.4)

val predator = Film("Predator", 1987, 7.9)
val huntForRedOctober = Film("The Hunt for Red October", 1990, 7.6)
val thomasCrownAffair = Film("The Thomas Crown Affair", 1999, 6.8)

val eastwood = Director("Clint", "Eastwood", 1930, Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))
val mcTiernan = Director("John", "McTiernan", 1951, Seq(predator, huntForRedOctober, thomasCrownAffair))

val nolan = Director("Christopher", "Nolan", 1970, Seq(memento, darkKnight, inception))

val someGuy = Director("Just", "Some Guy", 1990, Seq())

val directors = Seq(eastwood, mcTiernan, nolan, someGuy)

Calc.directorsWithBackCatalogOfSize(directors, 3).size
Calc.directorBornBefore(directors, 1971).size
Calc.directorBornBeforeWithBackCatalogOfSize(directors, 1971, 2).size

Calc.sort(directors)