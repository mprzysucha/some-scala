package helloakka

import akka.actor._

class HelloActor extends Actor {

  override def preStart(): Unit = {
    val secondActor = context.actorOf(Props[SecondActor], "secondActor")
    secondActor ! Msg
  }

  def receive: Receive = {
    case Message(a, b) => {
      println("Received: " + a + ", " + b)
      sender() ! Message("thank", "you")
    }
    case Msg => {
      println("Msg received")
    }
    context.stop(self)
  }

}

object HelloActor extends App {
  akka.Main.main(Array(classOf[HelloActor].getName))
}

class SecondActor extends Actor {
  override def receive: Receive = {
    case Msg => {
      println("Msg received")
      sender() ! Message("hello", "world")
    }
    case Message(a, b) => {
      println("Received: " + a + ", " + b)
      sender() ! Msg
    }
    context.stop(self)
  }
}

case object Msg

case class Message(a: String, b: String)
