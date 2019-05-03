package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph
import org.scalatest.{FunSuite, Matchers, OptionValues}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class TwoCityCalculationUnitTest extends FunSuite with Matchers with OptionValues {

  trait Fixture extends TwoCityCalculation {
    val graph: TrainGraph = Graph(WkDiEdge("A", "B")(2))
    val calculation: (graph.NodeT, graph.NodeT) => Option[Int] =
      (startCity: graph.NodeT, endCity: graph.NodeT) => Option(2)
  }

  test("should run the calculation function if the two cities both exist") {
    new Fixture {
      calculateIfCitiesExist("A", "B")(graph)(calculation).value shouldBe 2
      calculateIfCitiesExist("A", "A")(graph)(calculation).value shouldBe 2
    }
  }

  test("should not run the calculation function if either of the two cities do not exist") {
    new Fixture {
      calculateIfCitiesExist("A", "C")(graph)(calculation) shouldBe None
      calculateIfCitiesExist("C", "A")(graph)(calculation) shouldBe None
      calculateIfCitiesExist("C", "D")(graph)(calculation) shouldBe None
    }
  }
}
