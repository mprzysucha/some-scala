package projecteuler.problem66

import scala.math.{abs, sqrt}

/**
  * @author Michal Przysucha
  * @since 21.02.2016 16:48:00
  */
object Problem66 extends App {

  val maxD = 1000
  type Tripe = (BigInt, BigInt, BigInt)
  type Solution = (Int, BigInt)

  println(
    Iterator.from(2).take(maxD - 1).filter(sqrt(_) % 1 > 0)
      .map(d => new Solution(d, solve(d)))
      .foldLeft(new Solution(0, BigInt.apply(0)))((x, y) => tupleWithGreaterSecond(x, y))
  )

  def solve(n: Int): BigInt = {
    val a: BigInt = BigInt.apply(sqrt(1 + n).toLong + 1)
    tupleStream(n, Tuple3(a, 1, a * a - n)).filter(t => t._3 == 1).head._1
  }

  def newValues(a: BigInt, b: BigInt, k: BigInt, n: BigInt, m: BigInt): Tripe =
    Tuple3(
      (a * m + n * b) / k.abs,
      (a + b * m) / k.abs,
      (m * m - n) / k
    )

  def findM(a: BigInt, b: BigInt, k: BigInt, n: Int): BigInt =
    bigIntStream(BigInt.apply(abs(sqrt(4 * n) / 2).toLong)).filter(m => (a + b * m) % k == 0 && (m * m - n) % k == 0).head

  def bigIntStream(bigInt: BigInt): Stream[BigInt] =
    Stream.cons(bigInt, bigIntStream(bigInt + 1))

  def tupleStream(n: Int, tuple: Tripe): Stream[Tripe] =
    Stream.cons(tuple, tupleStream(n, newValues(tuple._1, tuple._2, tuple._3, n, findM(tuple._1, tuple._2, tuple._3, n))))

  def tupleWithGreaterSecond(t1: Solution, t2: Solution): Solution =
    if (t1._2 > t2._2) t1
    else t2

}
