import scala.io.StdIn._

object Main {
  def main(args: Array[String]): Unit = {
    def validate(line: String): Boolean =
      !line.startsWith("_") &&
        !line.endsWith("_") &&
        !line.contains("__") &&
        line.forall(c => c.isLower || c == '_')

    val line = readLine
    println(validate(line))

    val nextLine = "sjdhsh"
    println(nextLine)

    val anotherLine = "";
    println(anotherLine)
  }
}