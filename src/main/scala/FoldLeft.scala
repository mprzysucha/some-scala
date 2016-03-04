import scala.math.max

/**
  * @author Michal Przysucha
  * @since 03.03.2016 09:48:00
  */
object FoldLeft extends App {

  val l1: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val l2: List[Tuple2[String, Int]] = List(Tuple2("a", 1), Tuple2("x", 7), Tuple2("p", 3), Tuple2("b", 11), Tuple2("f", 4), Tuple2("k", 8))

  val maxValue = l1.foldLeft(0)((x, y) => max(x, y))
  val maxStr = l2.foldLeft("", Int.MinValue)((x, y) => maxTuple(x, y))

  def maxTuple(t1: Tuple2[String, Int], t2: Tuple2[String, Int]): Tuple2[String, Int] =
    if (t1._2 > t2._2) t1
    else t2

  println(maxValue)
  println(maxStr)

}
