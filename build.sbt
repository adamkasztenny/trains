organization := "adamkasztenny"

version := "1.0.0"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
    // I used this library, Graph for Scala for this project.
    // I consulted this documentation: https://scala-graph.org/guides/core-traversing.html
    "org.scala-graph" %% "graph-core" % "1.12.5"
)

libraryDependencies ++= Seq(
    // I also used ScalaTest for the unit tests, http://www.scalatest.org/
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
)
