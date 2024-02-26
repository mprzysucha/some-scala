package catseffect

import cats.Monad
import cats.effect.{ ExitCode, IO, IOApp, Ref }
import cats.syntax.all._

import scala.concurrent.duration._

final case class State(num: Double) extends AnyVal {
  def add(n: Double) = new State(num + n)
  def subtract(n: Double) = new State(num - n)
}

object State {
  val zero = State(0)
}

object PlayWithState extends IOApp {

  def sigmoid(s: IO[State]) = {
    Monad[IO].ifElseM(
      s.map(_.num > 0) -> s
    )(
      IO.pure(State.zero)
    )
  }

  def displaySigmoid(x: IO[State], y: IO[State]) = {
    (x, y).flatMapN { (x, y) =>
      IO.println(s"sigmoid(${x.num}): ${y.num}")
    }
  }

  def process(s: IO[State]) = {
    for {
      state <- sigmoid(s)
      original <- s
      _ <- IO.println(s"Going to sleep ${original.num.abs.seconds} seconds")
      _ <- IO.sleep(original.num.abs.seconds)
    } yield state
  }

  override def run(args: List[String]): IO[ExitCode] = for {
      stateRef <- Ref.of[IO, State](State.zero)
      _ <- stateRef.update(_.add(3))
      newState <- process(stateRef.get)
      _ <- displaySigmoid(stateRef.get, IO.pure(newState))
      _ <- stateRef.update(_.subtract(7))
      newState <- process(stateRef.get)
      _ <- displaySigmoid(stateRef.get, IO.pure(newState))
    } yield ExitCode.Success
}
