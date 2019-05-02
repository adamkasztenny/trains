package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.SampleGraph
import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class DistanceUnitTest extends FunSuite with Matchers with OptionValues {

  test("should return None for an empty graph, since there is no distance") {
    Distance("A", "B")(Graph.empty) shouldBe None
  }

  test("should return None if one of the cities does not exist") {
    val graph = Graph(WkDiEdge("A", "C")(2))
    Distance("A", "B")(graph) shouldBe None
    Distance("B", "A")(graph) shouldBe None
  }

  test("should return None if there is no route between two cities") {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "E")(2))
    Distance("A", "C")(graph) shouldBe None
  }

  test("should return the distance between two cities in a two node graph") {
    val graph = Graph(WkDiEdge("A", "B")(2))
    Distance("A", "B")(graph).value shouldBe 2
  }

  test("should return the distance between multiple cities in a graph with several nodes") {
    val graph = Graph(WkDiEdge("A", "B")(5), WkDiEdge("B", "C")(4), WkDiEdge("A", "C")(7))
    Distance("A", "C")(graph).value shouldBe 7
    Distance("A", "B", "C")(graph).value shouldBe 9
  }

  test("should return the distance between multiple cities in a graph with many nodes") {
    val graph = SampleGraph()
    Distance("A", "B", "C")(graph).value shouldBe 9
    Distance("A", "D")(graph).value shouldBe 5
    Distance("A", "D", "C")(graph).value shouldBe 13
    Distance("A", "E", "B", "C", "D")(graph).value shouldBe 22
    Distance("A", "E", "D")(graph) shouldBe None
  }
}
