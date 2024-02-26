package finaltagless

import cats.{Monad, _}
import cats.instances.future._
import zio.Task

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.util.Random

object FinalTagless {

  def main(args: Array[String]): Unit = {


    import zio.interop.catz._
    val zioService = new SomeService[zio.Task]

    val futureService = new SomeService[Future]

    val zioRes: Task[String] = zioService.op1(5)
    val futureRes: Future[String] = futureService.op1(5)

    display(zioRes)
    display(futureRes)
  }

  def display(fs: Future[String]) = {
    import scala.concurrent.duration._
    import scala.language.postfixOps
    println(Await.result(fs, 1 second))
  }

  def display(ts: Task[String]) = {
    println(zio.Runtime.default.run(ts))
  }

}

trait Algebra[F[_]] {
  def op1(i: Int): F[String]
}

class SomeService[F[_] : Monad] extends Algebra[F] {
  override def op1(i: Int): F[String] = implicitly[Monad[F]].pure(Random.alphanumeric.take(i).mkString)
}


