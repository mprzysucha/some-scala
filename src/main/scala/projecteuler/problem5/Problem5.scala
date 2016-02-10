package projecteuler.problem5

/**
  * The solution to Project Euler Problem 5 - Smallest multiple
  *
  * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
  * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
  *
  * https://projecteuler.net/problem=5
  *
  * @author Michal Przysucha
  */
object Problem5 extends App {

  type Num = Long

  val divisiors = (11 to 20).toList

  println(Iterator.from(start = 20, step = 20).dropWhile(!allRemindersEqualsZero(_)).next)

  def allRemindersEqualsZero(dividend: Num) =
    divisiors.dropWhile(dividend % _ == 0).isEmpty

}
