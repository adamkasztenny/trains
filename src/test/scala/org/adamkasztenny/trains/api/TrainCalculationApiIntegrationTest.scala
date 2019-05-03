package org.adamkasztenny.trains.api

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

  test("returns the number of trips between two cities on a graph, based on number of stops, if there is a path " +
    "between them") {
    new ConnectedCities {
      api.numberOfTripsBetweenCitiesByStops("A", "C", _ >= 1, 1) shouldBe "1"
    }
  }

  test("returns 0 for the number of trips between cities, based on number of stops, if the two " +
    "cities are not connected") {
    new DisconnectedCities {
      api.numberOfTripsBetweenCitiesByStops("A", "D", _ >= 1, 2) shouldBe  "0"
    }
  }

  test("returns no route message for the number of trips between cities, based on number of stops, if the two " +
    "cities do not exist") {
    new DisconnectedCities {
      api.numberOfTripsBetweenCitiesByStops("X", "Y", _ >= 1, 2) shouldBe api.NoRouteMessage
    }
  }

  test("returns the number of trips between two cities on a graph, based on distance, if there is a path between " +
    "them") {
    new ConnectedCities {
      api.numberOfTripsBetweenCitiesByDistance("A", "C", 10) shouldBe "2"
    }
  }

  test("returns 0 for the number of trips between cities, based on distance, if the two cities are " +
    "not connected") {
    new DisconnectedCities {
      api.numberOfTripsBetweenCitiesByDistance("A", "D", 2) shouldBe "0"
    }
  }

  test("returns no route message for the number of trips between cities, based on distance, if the two cities are " +
    "do not exist") {
    new DisconnectedCities {
      api.numberOfTripsBetweenCitiesByDistance("X", "Y", 2) shouldBe api.NoRouteMessage
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
