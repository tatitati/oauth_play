name := """play_oauth"""
organization := "blurbit"

val commonsSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.6"
)

val akkaVersion = "2.5.17"
val akkaHttpVersion = "10.1.7"

val thirdDependencies = Seq(
  // tools
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.github.nscala-time" %% "nscala-time" % "2.20.0",
  "com.typesafe.play" %% "play-json" % "2.6.10",

  // redis + mysql
  "net.debasishg" %% "redisclient" % "3.9",
  "mysql" % "mysql-connector-java" % "5.1.21",

  // slick
  "com.typesafe.play" %% "play-slick" % "3.0.0",
  //"com.typesafe.slick" %% "slick" % "3.3.0",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  // db pool
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",

  // dev
  "org.scalatest" %% "scalatest" % "3.0.5" % Test,
  "org.specs2" %% "specs2-core" % "4.3.4" % Test,
  jdbc % Test
)

scalacOptions in Test ++= Seq("-Yrangepos")

lazy val infrastructure = (project in file("subprojects/infrastructure"))
  .dependsOn(domain % "test->test;compile->compile")
  .settings(
    name := "infrastructure subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies,
    parallelExecution in Test := false
  )

lazy val domain = (project in file("subprojects/domain"))
  .settings(
    name := "domain subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
  )

lazy val learning = (project in file("subprojects/learning"))
  .settings(
    name := "learning subproject",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
  )

lazy val root = (project in file("."))
  .aggregate(learning, domain,infrastructure)
  .settings(
    name := "root project",
    commonsSettings,
    libraryDependencies ++= thirdDependencies
  )
