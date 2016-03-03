name := "somescala"

version := "1.0"

//scalaVersion := "2.10.2"
scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.1"
)

resolvers ++= Seq(
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
//  "Spray repository" at "http://repo.spray.io",
//  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
)