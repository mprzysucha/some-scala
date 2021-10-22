package freemonads

import cats.arrow.FunctionK
import cats.free.Free
import cats.~>
import cats.implicits._
import freemonads.Algebra.{Op1, Operations}
import zio.Task

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.util.Random
import zio.interop.catz._

object FreeMonads {

  def main(args: Array[String]): Unit = {

    val op1Free: Free[Operations, String] = Algebra.op1(5)

    val op1ResF: Future[String] = op1Free.foldMap(Transformers.toFuture)
    val op1ResFKP: Future[String] = op1Free.foldMap(Transformers.toFutureKindProjector)
    val op1ResFC: Future[String] = op1Free.foldMap(Transformers.toFutureCats)

    val op1ResT: Task[String] = op1Free.foldMap(Transformers.toTask)

    display(op1ResF)
    display(op1ResFKP)
    display(op1ResFC)
    display(op1ResT)


  }

  def display(fs: Future[String]) = {
    import scala.concurrent.duration._
    import scala.language.postfixOps
    println(Await.result(fs, 1 second))
  }

  def display(ts: Task[String]) = {
    println(zio.Runtime.default.unsafeRun(ts))
  }

}

object Algebra {

  sealed trait Operations[T]
  final case class Op1(i: Int) extends Operations[String]
  final case class Op2(S: String) extends Operations[Option[Int]]

  def op1(i: Int) = Free.liftF(Op1(i))

}

object Impl{

  def op1[A](i: Int) = Random.alphanumeric.take(i).mkString.asInstanceOf[A]

}

object Transformers {

  val toFuture = new FunctionK[Operations, Future] {
    override def apply[A](fa: Operations[A]): Future[A] = fa match {
      case Op1(i) => Future(Impl.op1[A](i))
    }
  }

  /*_*/
  val toFutureKindProjector = λ[FunctionK[Operations, Future]] {
    case Op1(i) => Future(Random.alphanumeric.take(i).mkString)
  }

  val toFutureCats = new (Operations ~> Future) {
    override def apply[A](fa: Operations[A]): Future[A] = fa match {
      case Op1(i) => Future(Impl.op1[A](i))
    }
  }

  val toTask = new FunctionK[Operations, Task] {
    override def apply[A](fa: Operations[A]): Task[A] = fa match {
      case Op1(i) => Task(Impl.op1[A](i))
    }
  }

}
