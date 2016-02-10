package projecteuler.problem4

/**
  * The solution to Project Euler Problem 4 - Largest palindrome product
  *
  * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
  * Find the largest palindrome made from the product of two 3-digit numbers.
  *
  * https://projecteuler.net/problem=4
  *
  * @author Michal Przysucha
  */
object Problem4 extends App {

  println(streamsNumbersDescending(999).flatMap(s => s.map(_ * s.head).filter(isPalindrome(_))).max)

  type Num = Long

  def isPalindrome(num: Num) = num.toString.reverse.toInt == num

  def streamsNumbersDescending(from: Num): Stream[Stream[Num]] = streamsNumbersDescending(from, 1)
  def streamsNumbersDescending(from: Num, to: Num): Stream[Stream[Num]] = streamsNumbersDescending(numbersDescending(from, to), to)

  def numbersDescending(from: Num, to: Num): Stream[Num] =
    if (from <= to) Stream.cons(from, Stream.empty)
    else Stream.cons(from, numbersDescending(from - 1, to))

  def streamsNumbersDescending(from: Stream[Num], to: Num): Stream[Stream[Num]] =
    if (from.head == to) Stream.cons(from, Stream.empty)
    else Stream.cons(numbersDescending(from.head, to), streamsNumbersDescending(from.tail, to))

}
