package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.SampleGraph
import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class NumberOfTripsByStopsUnitTest extends FunSuite with Matchers with OptionValues {

  private val simpleCompareTo: (Int) => Boolean = (length) => true
  private val maximumNumberOfStops = 5

  test("should return None for an empty graph, since there is no distance") {
    NumberOfTripsByStops("A", "B", simpleCompareTo, maximumNumberOfStops)(Graph.empty) shouldBe None
  }

  test("should return None if one of the cities does not exist") {
    val graph = Graph(WkDiEdge("A", "C")(2))
    NumberOfTripsByStops("A", "B", simpleCompareTo, maximumNumberOfStops)(graph) shouldBe None
    NumberOfTripsByStops("B", "A", simpleCompareTo, maximumNumberOfStops)(graph) shouldBe None
  }

  test("should return 0 if there is no route between two cities") {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "E")(2))
    NumberOfTripsByStops("A", "C", simpleCompareTo, maximumNumberOfStops)(graph).value shouldBe 0
  }

  test("should return the number of possible trips between two cities in a two node graph that satisfy the " +
    "comparison function") {
    val graph = Graph(WkDiEdge("A", "B")(2))
    NumberOfTripsByStops("A", "B", _ < 5, 4)(graph).value shouldBe 1
    NumberOfTripsByStops("A", "B", _ == 5, maximumNumberOfStops)(graph).value shouldBe 0
    NumberOfTripsByStops("A", "B", _ > 5, 10)(graph).value shouldBe 0
  }

  test("should return the number of possible trips between two cities in a graph with several nodes that satisfy the " +
    "comparison function") {
    val graph = Graph(WkDiEdge("A", "B")(4),  WkDiEdge("B", "C")(4), WkDiEdge("C", "D")(1), WkDiEdge("A", "D")(3))
    NumberOfTripsByStops("A", "D", _ < 5, 4)(graph).value shouldBe 2
    NumberOfTripsByStops("A", "D", _ == 5, maximumNumberOfStops)(graph).value shouldBe 0
    NumberOfTripsByStops("A", "D", _ > 1, 5)(graph).value shouldBe 1
  }

  test("should return the number of possible trips between two cities in a graph with many nodes that satisfy the " +
    "comparison function") {
    val graph = SampleGraph()
    NumberOfTripsByStops("C", "C", _ <= 3, 3)(graph).value shouldBe 2
    NumberOfTripsByStops("A", "C", _ == 4, 4)(graph).value shouldBe 3
  }
}
