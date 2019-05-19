import sbt._

object MyDependencies {

  val akkaVersion = "2.5.17"
  val akkaHttpVersion = "10.1.7"

  val thirdDependencies = Seq(
    // prod
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-stream" % akkaVersion,
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.github.nscala-time" %% "nscala-time" % "2.20.0",
    "com.typesafe.play" %% "play-json" % "2.6.10",
    "net.debasishg" %% "redisclient" % "3.9",

    // slick
    "mysql" % "mysql-connector-java" % "5.1.21",
    "com.typesafe.slick" %% "slick" % "3.3.0",
    "org.slf4j" % "slf4j-nop" % "1.6.4",
    "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",


    // dev
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
    "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
    "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test
  )
}
