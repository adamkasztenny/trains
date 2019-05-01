package org.adamkasztenny.trains.graph

import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class TrainGraphUnitTest extends FunSuite with Matchers with OptionValues {

  test("should create an empty graph if there is no text representation of the edges") {
    val trainGraph = TrainGraph(Array.empty)
    trainGraph shouldBe Graph.empty
    trainGraph.totalWeight shouldBe 0
  }

  test("should create a one node graph from the simple text representation of edges") {
    val trainGraph = TrainGraph(Array("CD8"))
    trainGraph shouldBe Graph(WkDiEdge("C", "D")(8))
    trainGraph.totalWeight shouldBe 8
  }

  test("should create a two node graph from the simple text representation of edges") {
    val trainGraph = TrainGraph(Array("BC4", "CD8"))
    trainGraph shouldBe Graph(WkDiEdge("B", "C")(4), WkDiEdge("C", "D")(8))
    trainGraph.totalWeight shouldBe 12
  }

  test("should create a graph with many nodes from the simple text representation of edges") {
    val textEdges = Array("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")
    val expectedGraph = Graph(
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
    val trainGraph = TrainGraph(textEdges)
    trainGraph shouldBe expectedGraph
    trainGraph.totalWeight shouldBe 48
  }
}
