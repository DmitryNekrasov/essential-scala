def numberOfVowels(str: String): Int =
  str.filter(Seq('a', 'e', 'i', 'o', 'u', 'y').contains(_)).length

implicit class ExtraStringMethods(str: String) {
  val vowels = Seq('a', 'e', 'i', 'o', 'u', 'y')

  def numberOfVowels: Int = str.filter(vowels contains _).length
}

val str = "the quick brown fox"
numberOfVowels(str)
new ExtraStringMethods(str).numberOfVowels
str.numberOfVowels