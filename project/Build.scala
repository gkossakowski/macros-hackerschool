import sbt._
import Keys._

object BuildSettings {
  val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := "gkossakowski",
    version := "1.0.0-SNAPSHOT",
    scalaVersion := "2.11.4",
    resolvers += Resolver.sonatypeRepo("snapshots"),
    resolvers += Resolver.sonatypeRepo("releases"),
    scalacOptions ++= Seq()
  )
}

object MacrosBuild extends Build {
  import BuildSettings._

  lazy val root: Project = Project(
    "root",
    file("."),
    settings = buildSettings ++ Seq(
      run <<= run in Compile in core)
  ) aggregate(macros, core, logging)

  lazy val macros: Project = Project(
    "macros",
    file("macros"),
    settings = buildSettings ++ Seq(
      libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
    )
  ) dependsOn logging

  lazy val core: Project = Project(
    "core",
    file("core"),
    settings = buildSettings :+ (scalacOptions += "-Xprint:typer")
  ) dependsOn(macros)

  lazy val logging: Project = Project(
    "logging",
    file("logging"),
    settings = buildSettings
  )
}
