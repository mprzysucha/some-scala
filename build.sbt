scalaVersion := "2.12.13"
version      := "0.1"

lazy val hello = (project in file("."))
  .settings(
    name := "somescala",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor"       % "2.6.16",
      "com.typesafe.akka" %% "akka-remote"      % "2.6.16",
      "org.apache.spark"  %% "spark-mllib"      % "3.1.2",
      "org.apache.spark"  %% "spark-core"       % "3.1.2",
      "dev.zio"           %% "zio"              % "1.0.12",
      "org.typelevel"     %% "cats-core"        % "2.6.1",
      "org.typelevel"     %% "cats-free"        % "2.6.1",
      "dev.zio"           %% "zio-interop-cats" % "2.5.1.0"
    ),
    addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.2" cross CrossVersion.full)
  )
