package org.adamkasztenny.trains

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph
import org.adamkasztenny.trains.graph.calculation.{DistanceCalculator, NumberOfTripsByDistanceCalculator, NumberOfTripsByStopsCalculator, ShortestPathCalculator}

class TrainCalculationApi(graph: TrainGraph) {

  val NoRouteMessage: String = "NO SUCH ROUTE"

  def distanceBetweenCities(cities: String*): String =
    DistanceCalculator(cities: _*)(graph) match {
      case Some(distance) => distance.toString
      case _ => NoRouteMessage
    }

  def numberOfTripsBetweenCitiesByStops(startCity: String, endCity: String, compareTo: (Int) => Boolean,
                                        maximumNumberOfStops: Int): String =
    NumberOfTripsByStopsCalculator(startCity, endCity, compareTo, maximumNumberOfStops)(graph) match {
      case Some(numberOfTrips) => numberOfTrips.toString
      case _ => NoRouteMessage
    }

  def numberOfTripsBetweenCitiesByDistance(startCity: String, endCity: String, maximumNumberOfStops: Int): String =
    NumberOfTripsByDistanceCalculator(startCity, endCity, maximumNumberOfStops)(graph) match {
      case Some(numberOfTrips) => numberOfTrips.toString
      case _ => NoRouteMessage
    }

  def shortestPathBetweenCities(startCity: String, endCity: String): String =
    ShortestPathCalculator(startCity, endCity)(graph) match {
      case Some(distance) => distance.toString
      case _ => NoRouteMessage
    }

}
