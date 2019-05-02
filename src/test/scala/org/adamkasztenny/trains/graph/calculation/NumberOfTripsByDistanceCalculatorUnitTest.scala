package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.SampleGraph
import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class NumberOfTripsByDistanceCalculatorUnitTest extends FunSuite with Matchers with OptionValues {

  private val maximumDistance = 5

  test("should return None for an empty graph, since there is no distance") {
    NumberOfTripsByDistanceCalculator("A", "B", maximumDistance)(Graph.empty) shouldBe None
  }

  test("should return None if one of the cities does not exist") {
    val graph = Graph(WkDiEdge("A", "C")(2))
    NumberOfTripsByDistanceCalculator("A", "B", maximumDistance)(graph) shouldBe None
    NumberOfTripsByDistanceCalculator("B", "A", maximumDistance)(graph) shouldBe None
  }

  test("should return None if there is no route between two cities") {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "E")(2))
    NumberOfTripsByDistanceCalculator("A", "C", maximumDistance)(graph) shouldBe None
  }

  test("should return the number of possible trips between two cities in a two node graph that have a distance less " +
    "than the one provided") {
    val graph = Graph(WkDiEdge("A", "B")(2))
    NumberOfTripsByDistanceCalculator("A", "B", 4)(graph).value shouldBe 1
    NumberOfTripsByDistanceCalculator("A", "B", 3)(graph).value shouldBe 1
    NumberOfTripsByDistanceCalculator("A", "B", 1)(graph) shouldBe None
    NumberOfTripsByDistanceCalculator("A", "B", 0)(graph) shouldBe None
  }

  test("should return the number of possible trips between two cities in a graph with many nodes that have a " +
    "distance less than the one provided") {
    val graph = SampleGraph()
    NumberOfTripsByDistanceCalculator("C", "C", 30)(graph).value shouldBe 7
  }
}
