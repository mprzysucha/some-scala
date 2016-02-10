package projecteuler.problem1

/**
  * The solution to Project Euler Problem 1 - Multiples of 3 and 5
  *
  * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
  * Find the sum of all the multiples of 3 or 5 below 1000.
  *
  * https://projecteuler.net/problem=1
  *
  * @author Michal Przysucha
  */
object Problem1 extends App {

  println(Stream.from(1).takeWhile(_ < 1000).filter(x => x % 3 == 0 || x % 5 == 0).sum)

}
