package projecteuler.problem57

/**
  * The solution to Project Euler Problem 57 - Square root convergents
  *
  * It is possible to show that the square root of two can be expressed as an infinite continued fraction.
  * √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
  *
  * By expanding this for the first four iterations, we get:
  *
  * 1 + 1/2 = 3/2 = 1.5
  * 1 + 1/(2 + 1/2) = 7/5 = 1.4
  * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
  * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
  *
  * The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion, 1393/985,
  * is the first example where the number of digits in the numerator exceeds the number of digits in the denominator.
  *
  * In the first one-thousand expansions, how many fractions contain a numerator with more digits than denominator?
  *
  * https://projecteuler.net/problem=57
  *
  * @author Michal Przysucha
  */
object Problem57 extends App {

  println(squareRootConvergents(new Fraction(3, 2)).take(1000).filter(f => f._1.toString.length > f._2.toString.length).toList.size)

  type Fraction = (BigInt, BigInt)

  def squareRootConvergents(fraction: Fraction): Stream[Fraction] =
    Stream.cons(fraction, squareRootConvergents(new Fraction(fraction._1 + fraction._2 * 2, fraction._1 + fraction._2)))

}
