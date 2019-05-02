package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.SampleGraph
import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class ShortestPathUnitTest extends FunSuite with Matchers with OptionValues {

  test("should return None for an empty graph, since there is no distance") {
    ShortestPath("A", "B")(Graph.empty) shouldBe None
  }

  test("should return None if one of the cities does not exist") {
    val graph = Graph(WkDiEdge("A", "C")(2))
    ShortestPath("A", "B")(graph) shouldBe None
    ShortestPath("B", "A")(graph) shouldBe None
  }

  test("should return None if there is no route between two cities") {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "E")(2))
    ShortestPath("A", "C")(graph) shouldBe None
  }

  test("should return the shortest path between two cities in a two node graph") {
    val graph = Graph(WkDiEdge("A", "B")(2))
    ShortestPath("A", "B")(graph).value shouldBe 2
  }

  test("should return the shortest path between multiple cities in a graph with several nodes") {
    val graphWithAToCCloser = Graph(WkDiEdge("A", "B")(5), WkDiEdge("B", "C")(4), WkDiEdge("A", "C")(1))
    ShortestPath("A", "C")(graphWithAToCCloser).value shouldBe 1

    val graphWithAToCFarther = Graph(WkDiEdge("A", "B")(5), WkDiEdge("B", "C")(4), WkDiEdge("A", "C")(10))
    ShortestPath("A", "C")(graphWithAToCFarther).value shouldBe 9
  }

  test("should return the shortest path between multiple cities in a graph with many nodes") {
    val graph = SampleGraph()
    ShortestPath("A", "C")(graph).value shouldBe 9
    ShortestPath("B", "B")(graph).value shouldBe 9
  }

  test("should return the shortest path back to the starting node") {
    val graphWithOneCycle = Graph(WkDiEdge("A", "B")(5), WkDiEdge("B", "C")(4), WkDiEdge("C", "A")(1))
    ShortestPath("A", "A")(graphWithOneCycle).value shouldBe 10

    val graphWithMultipleCycles = Graph(
      WkDiEdge("A", "B")(5),
      WkDiEdge("B", "C")(4),
      WkDiEdge("C", "A")(1),
      WkDiEdge("A", "D")(1),
      WkDiEdge("D", "E")(1),
      WkDiEdge("E", "A")(1)
    )
    ShortestPath("A", "A")(graphWithMultipleCycles).value shouldBe 3
  }
}
