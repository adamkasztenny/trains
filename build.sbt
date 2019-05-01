organization := "adamkasztenny"

version := "1.0.0"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
    "org.scala-graph" %% "graph-core" % "1.12.5"
)

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test
)
