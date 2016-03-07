name := "somescala"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor_2.11" % "2.4.1"
//  ,"com.typesafe.akka" %% "akka-remote" % "2.4.1"
)

resolvers ++= Seq(
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
//  ,"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
//  ,"Spray repository" at "http://repo.spray.io"
)