import scala.io.StdIn._

object Main {
  def main(args: Array[String]): Unit = {
    def validate(line: String) =
      !line.startsWith("_") &&
        !line.endsWith("_") &&
        !line.contains("__") &&
        line.forall(c => c.isLower || c == '_')

    val line = readLine
    println(validate(line))
  }
}