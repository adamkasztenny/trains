package org.adamkasztenny.trains.graph.calculation

import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class DistanceCalculatorUnitTest extends FunSuite with Matchers with OptionValues {

  test("should return None for an empty graph, since there is no distance") {
    DistanceCalculator("A", "B")(Graph.empty) shouldBe None
  }

  test("should return None if one of the cities does not exist") {
    val graph = Graph(WkDiEdge("A", "C")(2))
    DistanceCalculator("A", "B")(graph) shouldBe None
    DistanceCalculator("B", "A")(graph) shouldBe None
  }

  test("should return None if there is no route between two cities") {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "E")(2))
    DistanceCalculator("A", "C")(graph) shouldBe None
  }

  test("should return the distance between two cities in a one node graph") {
    val graph = Graph(WkDiEdge("A", "B")(2))
    DistanceCalculator("A", "B")(graph).value shouldBe 2
  }

  test("should return the distance between multiple cities in a graph with several nodes") {
    val graph = Graph(WkDiEdge("A", "B")(5), WkDiEdge("B", "C")(4), WkDiEdge("A", "C")(7))
    DistanceCalculator("A", "C")(graph).value shouldBe 7
    DistanceCalculator("A", "B", "C")(graph).value shouldBe 9
  }

  test("should return the distance between multiple cities in a graph with many nodes") {
    val graph = Graph(
      WkDiEdge("A", "B")(5),
      WkDiEdge("B", "C")(4),
      WkDiEdge("C", "D")(8),
      WkDiEdge("D", "C")(8),
      WkDiEdge("D", "E")(6),
      WkDiEdge("A", "D")(5),
      WkDiEdge("C", "E")(2),
      WkDiEdge("E", "B")(3),
      WkDiEdge("A", "E")(7)
    )
    DistanceCalculator("A", "B", "C")(graph).value shouldBe 9
    DistanceCalculator("A", "D")(graph).value shouldBe 5
    DistanceCalculator("A", "D", "C")(graph).value shouldBe 13
    DistanceCalculator("A", "E", "B", "C", "D")(graph).value shouldBe 22
    DistanceCalculator("A", "E", "D")(graph) shouldBe None
  }
}
