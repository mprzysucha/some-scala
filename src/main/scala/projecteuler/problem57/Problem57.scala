package projecteuler.problem57

/**
  * @author Michal Przysucha
  * @since 04.03.2016 16:56:00
  */
object Problem57 extends App {

  println(squareRootConvergents(new Fraction(3, 2)).take(1000).filter(f => f._1.toString.length > f._2.toString.length).toList.size)

  type Fraction = (BigInt, BigInt)

  def squareRootConvergents(fraction: Fraction): Stream[Fraction] =
    Stream.cons(fraction, squareRootConvergents(new Fraction(fraction._1 + fraction._2 * 2, fraction._1 + fraction._2)))

}
