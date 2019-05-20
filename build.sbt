name := """play_oauth"""
organization := "blurbit"

//lazy val root = (project in file(".")).enablePlugins(PlayScala)

val commonsSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.6"
)

val akkaVersion = "2.5.17"
val akkaHttpVersion = "10.1.7"

val thirdDependencies = Seq(
  // akka
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,

  // tools
  "com.github.nscala-time" %% "nscala-time" % "2.20.0",
  "com.typesafe.play" %% "play-json" % "2.6.10",

  // redis
  "net.debasishg" %% "redisclient" % "3.9",

  // slick
  //    "com.typesafe.play" %% "play-slick" % "3.0.1",
  "com.typesafe.play" %% "play-slick-evolutions" % "3.0.1",
  "mysql" % "mysql-connector-java" % "5.1.21",
  "com.typesafe.slick" %% "slick" % "3.3.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",

  // dev
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test,
  "org.specs2" %% "specs2-core" % "4.3.4" % Test,
  jdbc % Test
)

scalacOptions in Test ++= Seq("-Yrangepos")

lazy val infrastructure = (project in file("infrastructure"))
  .dependsOn(domain % "test->test;compile->compile")
  .settings(
    name := "infrastructure subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies,
    parallelExecution in Test := false
  )

lazy val domain = (project in file("domain"))
  .settings(
    name := "domain subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
  )

lazy val root = (project in file("."))
  .aggregate(domain,infrastructure)
  .settings(
    name := "root project",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
  )
