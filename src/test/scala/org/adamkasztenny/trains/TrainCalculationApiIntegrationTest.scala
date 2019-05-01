package org.adamkasztenny.trains

import org.scalatest.{FunSuite, Matchers}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class TrainCalculationApiIntegrationTest extends FunSuite with Matchers {

  test("returns the distance between two cities on a graph if there is a path between them") {
    val graph = Graph(WkDiEdge("A", "B")(2))
    val api = new TrainCalculationApi(graph)
    api.distanceBetweenCities("A", "B") shouldBe "2"
  }

  test("returns a no route message if the two cities are not connected") {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "D")(3))
    val api = new TrainCalculationApi(graph)
    api.distanceBetweenCities("A", "D") shouldBe "NO SUCH ROUTE"
  }
}
