
import MyDependencies._

//lazy val root = (project in file(".")).enablePlugins(PlayScala)
// Adds additional packages into Twirl
//TwirlKeys.templateImports += "blurbit.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "blurbit.binders._"


name := """play_oauth"""
organization := "blurbit"

//lazy val root = (project in file(".")).enablePlugins(PlayScala)

val commonsSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.6"
)


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
