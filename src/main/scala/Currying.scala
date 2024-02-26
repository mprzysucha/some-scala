import scala.language.postfixOps

object Currying {

  def abc(a: String)(b: String): Int = {
    a concat b length
  }

  def qwe(x: String => Int): Int = {
    x.apply("xyz")
  }

  def main(args: Array[String]): Unit = {
    println(qwe(abc("abcd")))
  }

}
