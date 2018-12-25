//global settings using ThisBuild
ThisBuild / scalaVersion := "2.11.7"
ThisBuild / organization := "io.typesql"
ThisBuild / version := "0.1"

//https://www.scala-sbt.org/1.0/docs/Cross-Build.html
ThisBuild / crossScalaVersions := Seq(
  /** For scala 2.10 to work blackbox.Context should be changed to Context
    * in scala 2.11 and above macro context was separated as blackbox and whitebox
    * "2.10.2", "2.10.3", "2.10.4", "2.10.5", "2.10.6"
    */
  "2.11.0", "2.11.1", "2.11.2", "2.11.3", "2.11.4","2.11.5", "2.11.6", "2.11.7", "2.11.8",
  "2.12.0", "2.12.1", "2.12.2", "2.12.3", "2.12.4", "2.12.5", "2.12.6"
)

val paradiseVersion = "2.1.0"
/**
  * For scala version 2.13 and above scalamacros paradise will be folded into scala compiler itself
  * and libraryDependencies can be removed after scala upgrade
  **/
//val paradiseCompilePlugin = addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)
val paradiseCompilePlugin = addCompilerPlugin("org.scalamacros" %% "paradise" % paradiseVersion cross CrossVersion.full)
lazy val root = (project in file("."))
  .aggregate(macrosModule, macrosModuleTesting)  //broadcasts commands to submodule
  .settings(
  name := "root",
  paradiseCompilePlugin
)


lazy val macrosModule = (project in file("macros"))
  .settings(
    name := "macros",
    libraryDependencies += scalaVersion("org.scala-lang" % "scala-reflect" % _).value,
    libraryDependencies ++= (
      if (scalaVersion.value.startsWith("2.10")) List("org.scalamacros" %% "quasiquotes" % paradiseVersion)
      else Nil
    ),
    paradiseCompilePlugin
  )

lazy val macrosModuleTesting = (project in file("testing"))
  .dependsOn(macrosModule)
  .settings(
    name := "testing",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    paradiseCompilePlugin
  )