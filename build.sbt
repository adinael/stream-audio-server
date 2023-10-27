ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

lazy val root = (project in file("."))
  .settings(
    name := "akka streams"
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.5.2",
  "com.typesafe.akka" %% "akka-stream" % "2.8.3",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.5.2" % Test,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % "2.8.3" % Test,
  "org.scalatest" %% "scalatest" % "3.3.0-SNAP4" % Test,
  "ch.qos.logback" % "logback-classic" % "1.4.8" % Test
)
