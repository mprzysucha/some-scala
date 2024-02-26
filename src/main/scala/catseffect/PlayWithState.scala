package catseffect

import cats.Monad
import cats.effect.{ ExitCode, IO, IOApp, Ref }

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

  override def run(args: List[String]): IO[ExitCode] = {

    for {
      stateRef <- Ref.of[IO, State](State.zero)
      _ <- stateRef.update(_.add(4))
      sigmoid1 <- sigmoid(stateRef.get)
      _ <- IO.println(sigmoid1)
      _ <- stateRef.update(_.subtract(7))
      sigmoid2 <- sigmoid(stateRef.get)
      _ <- IO.println(sigmoid2)
    } yield ExitCode.Success

  }
}
