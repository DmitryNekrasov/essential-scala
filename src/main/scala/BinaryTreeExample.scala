object BinaryTreeExample extends App {
  sealed trait Tree {
    final def sum: Int =
      this match {
        case Leaf(key) => key
        case Node(left, right, key) => left.sum + right.sum + key
      }

    final def double: Tree =
      this match {
        case Leaf(key) => Leaf(key * 2)
        case Node(left, right, key) => Node(left.double, right.double, key * 2)
      }

    final def asString: String =
      this match {
        case Leaf(key) => s"${key.toString}"
        case Node(left, right, key) => s"[${key.toString}, (left: ${left.asString}, right: ${right.asString})]"
      }
  }
  final case class Node(left: Tree, right: Tree, key: Int) extends Tree
  final case class Leaf(key: Int) extends Tree

  val tree = Node(Node(Leaf(4), Leaf(5), 2), Node(Leaf(6), Leaf(7), 3), 1)
  println(tree.sum)
  println(tree.asString)
  println(tree.double.asString)

  assert(tree.sum == 28)
}
