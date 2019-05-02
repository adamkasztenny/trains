package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.SampleGraph
import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class NumberOfTripsCalculatorUnitTest extends FunSuite with Matchers with OptionValues {

  private val simpleCompareTo: (Int) => Boolean = (length) => true
  private val maximumNumberOfStops = 5

  test("should return None for an empty graph, since there is no distance") {
    NumberOfTripsCalculator("A", "B", simpleCompareTo, maximumNumberOfStops)(Graph.empty) shouldBe None
  }

  test("should return None if one of the cities does not exist") {
    val graph = Graph(WkDiEdge("A", "C")(2))
    NumberOfTripsCalculator("A", "B", simpleCompareTo, maximumNumberOfStops)(graph) shouldBe None
    NumberOfTripsCalculator("B", "A", simpleCompareTo, maximumNumberOfStops)(graph) shouldBe None
  }

  test("should return None if there is no route between two cities") {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "E")(2))
    NumberOfTripsCalculator("A", "C", simpleCompareTo, maximumNumberOfStops)(graph) shouldBe None
  }

  test("should return the number of possible trips between two cities in a two node graph that satisfy the " +
    "comparison function") {
    val graph = Graph(WkDiEdge("A", "B")(2))
    NumberOfTripsCalculator("A", "B", _ < 5, 4)(graph).value shouldBe 1
    NumberOfTripsCalculator("A", "B", _ == 5, maximumNumberOfStops)(graph) shouldBe None
    NumberOfTripsCalculator("A", "B", _ > 5, 10)(graph) shouldBe None
  }

  test("should return the number of possible trips between two cities in a graph with many nodes that satisfy the " +
    "comparison function") {
    val graph = SampleGraph()
    NumberOfTripsCalculator("C", "C", _ <= 3, 3)(graph).value shouldBe 2
    NumberOfTripsCalculator("A", "C", _ == 4, 4)(graph).value shouldBe 3
  }
}
