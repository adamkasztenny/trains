package org.adamkasztenny.trains

import org.scalatest.{FunSuite, Matchers}

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class TrainCalculationApiIntegrationTest extends FunSuite with Matchers {

  trait ConnectedCities {
    val graph = Graph(WkDiEdge("A", "B")(5), WkDiEdge("B", "C")(4), WkDiEdge("A", "C")(7))
    val api = new TrainCalculationApi(graph)
  }

  trait DisconnectedCities {
    val graph = Graph(WkDiEdge("A", "B")(2), WkDiEdge("C", "D")(3))
    val api = new TrainCalculationApi(graph)
  }

  test("returns the distance between two cities on a graph if there is a path between them") {
    new ConnectedCities {
      api.distanceBetweenCities("A", "B", "C") shouldBe "9"
    }
  }

  test("returns a no route message for distance between cities if the two cities are not connected") {
    new DisconnectedCities {
      api.distanceBetweenCities("A", "D") shouldBe api.NoRouteMessage
    }
  }

  test("returns the shortest path between two cities on a graph if there is a path between them") {
    new ConnectedCities {
      api.shortestPathBetweenCities("A", "C") shouldBe "7"
    }
  }

  test("returns a no route message for the shortest path between cities if the two cities are not connected") {
    new DisconnectedCities {
      api.shortestPathBetweenCities("A", "D") shouldBe api.NoRouteMessage
    }
  }
}
