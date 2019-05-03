package org.adamkasztenny.trains.api

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph
import org.adamkasztenny.trains.graph.calculation.{Distance, NumberOfTripsByDistance, NumberOfTripsByStops, ShortestPath}

class TrainCalculationApi(graph: TrainGraph) {

  val NoRouteMessage: String = "NO SUCH ROUTE"

  def distanceBetweenCities(cities: String*): String = resultOrNoRoute(Distance(cities: _*)(graph))

  def numberOfTripsBetweenCitiesByStops(startCity: String, endCity: String, compareTo: (Int) => Boolean,
                                        maximumNumberOfStops: Int): String =
    resultOrNoRoute(NumberOfTripsByStops(startCity, endCity, compareTo, maximumNumberOfStops)(graph))

  def numberOfTripsBetweenCitiesByDistance(startCity: String, endCity: String, maximumNumberOfStops: Int): String =
    resultOrNoRoute(NumberOfTripsByDistance(startCity, endCity, maximumNumberOfStops)(graph))

  def shortestPathBetweenCities(startCity: String, endCity: String): String =
    resultOrNoRoute(ShortestPath(startCity, endCity)(graph))

  private def resultOrNoRoute(result: Option[Int]): String = result.map(_.toString).getOrElse(NoRouteMessage)
}
