package projecteuler.problem66

import scala.math.{abs, sqrt}

/**
  * The solution to Project Euler Problem 66 - Diophantine equation
  *
  * Consider quadratic Diophantine equations of the form:
  *
  * x^2 – Dy^2 = 1
  *
  * For example, when D=13, the minimal solution in x is 649^2 – 13×180^2 = 1.
  *
  * It can be assumed that there are no solutions in positive integers when D is square.
  *
  * By finding minimal solutions in x for D = {2, 3, 5, 6, 7}, we obtain the following:
  *
  * 3^2 – 2×2^2 = 1
  * 2^2 – 3×1^2 = 1
  * 9^2 – 5×4^2 = 1
  * 5^2 – 6×2^2 = 1
  * 8^2 – 7×3^2 = 1
  *
  * Hence, by considering minimal solutions in x for D ≤ 7, the largest x is obtained when D=5.
  *
  * Find the value of D ≤ 1000 in minimal solutions of x for which the largest value of x is obtained.
  *
  * https://projecteuler.net/problem=66
  *
  * @author Michal Przysucha
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
