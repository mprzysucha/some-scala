package projecteuler.problem6

/**
  * @author Michal Przysucha
  * @since 21.02.2016 15:59:00
  */
object Problem6 extends App {

  type Num = Long

  def pow(x: Num): Num = x * x
  val squaredSum = 100 / 2 * (1 + 100) * 100 / 2 * (1 + 100)
  val sumOfSquares = Iterator.from(1).take(100).map(pow(_)).sum

  println(squaredSum - sumOfSquares)


}
