package projecteuler.problem7

/**
  * @author Michal Przysucha
  * @since 21.02.2016 14:29:00
  */
object Problem7 extends App {

//  println (primes.take(6).toList.last)
  println (primes.take(10001).toList.last)

  type Num = Int

  def primes: Stream[Num] = primes(numbers)
  def primes(numbers: Stream[Num]): Stream[Num] = Stream.cons(numbers.head, primes(numbers.tail.filter(_ % numbers.head != 0)))
  def numbers: Stream[Num] = numbers(2)
  def numbers(from: Num): Stream[Num] = Stream.cons(from, numbers(from  + 1))

}
