package projecteuler.problem66

import scala.math.{abs, sqrt}

/**
  * @author Michal Przysucha
  * @since 21.02.2016 16:48:00
  */
object Problem66 extends App {

  println(Iterator.from(2).take(999).filter(sqrt(_) % 1 > 0).map(d => Tuple2(d, solve(d))).toList.sortWith((t1, t2) => t1._2 > t2._2).head._1)

  def solve(n: Int): BigInt = {
    val a: BigInt = BigInt.apply(sqrt(1 + n).toLong + 1)
    tupleStream(n, Tuple3(a, 1, a * a - n)).filter(t => t._3 == 1).head._1
  }

  def newValues(a: BigInt, b: BigInt, k: BigInt, n: BigInt, m: BigInt): Tuple3[BigInt, BigInt, BigInt] =
    Tuple3((a * m + n * b) / k.abs, (a + b * m) / k.abs, (m * m - n) / k)

  def findM(a: BigInt, b: BigInt, k: BigInt, n: Int): BigInt =
    bigIntStream(BigInt.apply(abs(sqrt(4 * n) / 2).toLong)).filter(m => (a + b * m) % k == 0 && (m * m - n) % k == 0).head

  def bigIntStream(bigInt: BigInt): Stream[BigInt] =
    Stream.cons(bigInt, bigIntStream(bigInt + 1))

  def tupleStream(n: Int, tuple: Tuple3[BigInt, BigInt, BigInt]): Stream[Tuple3[BigInt, BigInt, BigInt]] =
    Stream.cons(tuple, tupleStream(n, newValues(tuple._1, tuple._2, tuple._3, n, findM(tuple._1, tuple._2, tuple._3, n))))

}
