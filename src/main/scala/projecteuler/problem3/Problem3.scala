package projecteuler.problem3

/**
  * The solution to Project Euler Problem 3 - Largest prime factor
  *
  * The prime factors of 13195 are 5, 7, 13 and 29.
  * What is the largest prime factor of the number 600851475143 ?
  *
  * https://projecteuler.net/problem=3
  *
  * @author Michal Przysucha
  */
object Problem3 extends App {

  println(primes.take(10).toList)
  println(factors(600851475143L).last)

  type Num = Long

  def factors(num: Num): List[Num] = factors(num, primes)

  def factors(num: Num, primes: Stream[Num]): List[Num] = num match {
    case 1 => List()
    case _ if (num % primes.head == 0) => primes.head :: factors(num / primes.head, primes)
    case _ => factors(num, primes.tail)
  }

  def primes: Stream[Num] = primes(numbers)

  def primes(numbers: Stream[Num]): Stream[Num] =
    Stream.cons(numbers.head, primes(numbers.tail.filter(_ % numbers.head != 0)))

  def numbers: Stream[Num] = numbers(2)

  def numbers(head: Num): Stream[Num] = Stream.cons(head, numbers(head + 1))

}
