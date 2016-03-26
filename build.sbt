name := "somescala"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.1"
  ,"org.apache.spark" % "spark-mllib_2.11" % "1.6.0"
  ,"org.apache.spark" % "spark-core_2.11" % "1.6.0"
//  ,"com.typesafe.akka" %% "akka-remote" % "2.4.1"
)

resolvers ++= Seq(
  "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
  ,"Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  ,"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
//  ,"Spray repository" at "http://repo.spray.io"
)