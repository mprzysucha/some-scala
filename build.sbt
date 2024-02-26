scalaVersion := "2.13.12"
version      := "0.1"

val akkaV = "2.8.5"
val catsEffectV = "3.5.3"
val catsV = "2.6.1"
val sparkV = "3.5.1"
val zioV = "2.0.21"

lazy val hello = (project in file("."))
  .settings(
    name := "somescala",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor"       % akkaV,
      "com.typesafe.akka" %% "akka-remote"      % akkaV,
      "org.apache.spark"  %% "spark-mllib"      % sparkV,
      "org.apache.spark"  %% "spark-core"       % sparkV,
      "dev.zio"           %% "zio"              % zioV,
      "dev.zio"           %% "zio-interop-cats" % "23.1.0.1",
      "org.typelevel"     %% "cats-core"        % catsV,
      "org.typelevel"     %% "cats-free"        % catsV,
      "org.typelevel" %% "cats-effect" % catsEffectV withSources() withJavadoc(),
      "org.typelevel" %% "cats-effect-cps" % "0.4.0"
    ),
    addCompilerPlugin("org.typelevel" % "kind-projector" % "0.13.3" cross CrossVersion.full)
  )
