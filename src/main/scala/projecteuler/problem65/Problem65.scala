package projecteuler.problem65

/**
  * @author Michal Przysucha
  */
object Problem65 extends App {

  println(numerators.take(100).last.toString.split("").toList.map(x => x.toInt).foldLeft(0)(_ + _))

  def numerators: Stream[BigInt] = numerators(0, 0, 0)

  def numerators(n: BigInt, prev: BigInt, prev2: BigInt): Stream[BigInt] =
    if (n == 0) Stream.cons(2, numerators(n + 1, 2, 0))
    else if (n == 1) Stream.cons(3, numerators(n + 1, 3, 2))
    else Stream.cons(fraction(n) * prev + prev2, numerators(n + 1, fraction(n) * prev + prev2, prev))

  def fraction(n: BigInt): BigInt =
    if (n == 0) 2
    else if (n % 3 == 2) 2 * (n + 1) / 3
    else 1

}
