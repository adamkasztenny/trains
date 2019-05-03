package org.adamkasztenny.trains.graph

import org.scalatest.{FunSuite, Matchers, OptionValues}

import scala.util.Random
import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class TrainGraphBuilderUnitTest extends FunSuite with Matchers with OptionValues {

  private val sampleGraphTextEdges = Array("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7")

  test("should create an empty graph if there is no text representation of the edges") {
    val trainGraph = TrainGraphBuilder(Array.empty)
    trainGraph shouldBe Graph.empty
    trainGraph.totalWeight shouldBe 0
  }

  test("should create a one node graph from the simple text representation of edges") {
    val trainGraph = TrainGraphBuilder(Array("CD8"))
    trainGraph shouldBe Graph(WkDiEdge("C", "D")(8))
    trainGraph.totalWeight shouldBe 8
  }

  test("should create a two node graph from the simple text representation of edges") {
    val trainGraph = TrainGraphBuilder(Array("BC4", "CD8"))
    trainGraph shouldBe Graph(WkDiEdge("B", "C")(4), WkDiEdge("C", "D")(8))
    trainGraph.totalWeight shouldBe 12
  }

  test("should create a graph with many nodes from the simple text representation of edges") {
    val expectedGraph = SampleGraph()
    val trainGraph = TrainGraphBuilder(sampleGraphTextEdges)
    trainGraph shouldBe expectedGraph
    trainGraph.totalWeight shouldBe 48
  }

  test("should create a graph with multi-digit distances") {
    val textEdges = Array("AB300", "BC10")
    val expectedGraph = Graph(WkDiEdge("A", "B")(300), WkDiEdge("B", "C")(10))
    val trainGraph = TrainGraphBuilder(textEdges)
    trainGraph shouldBe expectedGraph
  }

  test("should produce the same graph regardless of order of the inputs") {
    TrainGraphBuilder(sampleGraphTextEdges) shouldBe
      TrainGraphBuilder(Random.shuffle(sampleGraphTextEdges.toSeq).toArray)
  }

  test("should produce a disjoint graph") {
    val textEdges = Array("AC6", "BD2", "DE8", "DB7")
    val expectedGraph = Graph(WkDiEdge("A", "C")(6), WkDiEdge("B", "D")(2), WkDiEdge("D", "B")(7),
      WkDiEdge("D", "E")(8))
    TrainGraphBuilder(textEdges) shouldBe expectedGraph
  }
}
