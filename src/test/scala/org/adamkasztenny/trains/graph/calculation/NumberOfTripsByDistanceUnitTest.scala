package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.SampleGraph
import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class NumberOfTripsByDistanceUnitTest extends FunSuite with Matchers with OptionValues {

  private val maximumDistance = 5

  test("should return None for an empty graph, since there is no distance") {
    NumberOfTripsByDistance("A", "B", maximumDistance)(Graph.empty) shouldBe None
  }

  test("should return None if one of the cities does not exist") {
    val graph = Graph(WkDiEdge("A", "C")(2))
    NumberOfTripsByDistance("A", "B", maximumDistance)(graph) shouldBe None
    NumberOfTripsByDistance("B", "A", maximumDistance)(graph) shouldBe None
  }

  test("should return None if there is no route between two cities") {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "E")(2))
    NumberOfTripsByDistance("A", "C", maximumDistance)(graph) shouldBe None
  }

  test("should return the number of possible trips between two cities in a two node graph that have a distance less " +
    "than the one provided") {
    val graph = Graph(WkDiEdge("A", "B")(2))
    NumberOfTripsByDistance("A", "B", 4)(graph).value shouldBe 1
    NumberOfTripsByDistance("A", "B", 3)(graph).value shouldBe 1
    NumberOfTripsByDistance("A", "B", 1)(graph) shouldBe None
    NumberOfTripsByDistance("A", "B", 0)(graph) shouldBe None
  }

  test("should return the number of possible trips between two cities in a graph with many nodes that have a " +
    "distance less than the one provided") {
    val graph = SampleGraph()
    NumberOfTripsByDistance("C", "C", 30)(graph).value shouldBe 7
  }
}
