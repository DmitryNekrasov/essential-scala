object GenericBinaryTree extends App {
  sealed trait Tree[A] {
    final def asString: String = fold(_.toString)(_ + " " + _)

    final def fold[B](leaf: A => B)(node: (B, B) => B) : B =
      this match {
        case Leaf(key) => leaf(key)
        case Node(left, right) => node(left.fold(leaf)(node), right.fold(leaf)(node))
      }
  }
  final case class Node[A](left: Tree[A], right: Tree[A]) extends Tree[A]
  final case class Leaf[A](key: A) extends Tree[A]

  val tree: Tree[String] =
    Node(Node(Leaf("To"), Leaf("iterate")),
      Node(Node(Leaf("is"), Leaf("human,")),
        Node(Leaf("to"), Node(Leaf("recurse"), Leaf("divine")))))

  println(tree.asString)
}
