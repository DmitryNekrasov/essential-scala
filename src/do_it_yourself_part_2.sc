def unionOfSets[A](set1: Set[A], set2: Set[A]): Set[A] =
  set1.foldLeft(set2)((set, element) => set + element)

def unionOfMaps[A, B](map1: Map[A, B], map2: Map[A, B])(f: (B, B) => B): Map[A, B] =
  map1.foldLeft(map2)((res, pair) => {
    val (key, value) = pair
    res + (key -> res.get(key).map(f(_, value)).getOrElse(value))
  })

val set1 = Set(1, 2, 3)
val set2 = Set(4, 5, 6, 7)
unionOfSets(set1, set2)

unionOfMaps(Map('a' -> 1, 'b' -> 2), Map('a' -> 2, 'b' -> 4, 'c' -> 8))(_ + _)