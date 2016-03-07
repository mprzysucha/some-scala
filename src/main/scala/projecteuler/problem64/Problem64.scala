package projecteuler.problem64

import scala.math.sqrt

/**
  * The solution to Project Euler Problem 64 - Odd period square roots
  *
  * https://projecteuler.net/problem=64
  *
  * @author Michal Przysucha
  */
object Problem64 extends App {

  val periods = for (n <- 2 to 10000 if (sqrt(n) % 1 != 0)) yield find(n)
  println(periods.toList.filter(x => x % 2 != 0).size)

  def find(n: Long): Int = {
    find(triples(n), Map.empty[Triple, Int])
  }

  def find(ts: Stream[Triple], m: Map[Triple, Int]): Int =
    if (m contains ts.head) m get ts.head get
    else find(ts.tail, (m + (ts.head -> 0)).mapValues(x => x + 1))

  type Triple = Tuple3[Long, Long, Long]

  def triples(n: Long): Stream[Triple] = {
    val s1 = sqrt(n).toLong
    triples(n, s1, new Triple(s1, 0, 1))
  }

  def triples(n: Long, s1: Long, t: Triple): Stream[Triple] = {
    val newA = -t._2 + t._1 * t._3
    val newB = (n - newA * newA) / t._3
    val newS = (s1 + newA) / newB
    val newT = new Triple(newS, newA, newB)
    Stream.cons(t, triples(n, s1, newT))
  }

}