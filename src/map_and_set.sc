val map = Map("a" -> 1, "b" -> 2, "c" -> 3)
map("a")
map.get("e")
map.getOrElse("d", -1)

map.contains("a")

map + ("q" -> 5) + ("w" -> 7)
map - "a"

val map1 = Map("a" -> 1, "b" -> 2)
val map2 = Map("c" -> 3)
map1 ++ map2

val mutableMap = scala.collection.mutable.Map("q" -> 11, "w" -> 22, "e" -> 33)
mutableMap += ("g" -> 7)
mutableMap("q") = 100
mutableMap("zzz") = 101
mutableMap

map.flatMap {
  case (str, num) =>
    (1 to 3).map(x => (str + x) -> num * x)
}

map.map { case (s, n) => s -> n * 10 }