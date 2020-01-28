import scala.collection.mutable

val animals = Seq("cat", "dog", "penguin")
"mouse" +: animals :+ "tyrannosaurus"
1 +: animals

val mutAnimals = mutable.Seq("cat", "dog", "penguin")
mutAnimals(0) = "cheetah"