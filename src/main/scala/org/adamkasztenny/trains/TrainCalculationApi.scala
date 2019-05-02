package org.adamkasztenny.trains

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph
import org.adamkasztenny.trains.graph.calculation.{DistanceCalculator, NumberOfTripsCalculator, ShortestPathCalculator}

class TrainCalculationApi(graph: TrainGraph) {

  val NoRouteMessage: String = "NO SUCH ROUTE"

  def distanceBetweenCities(cities: String*): String =
    DistanceCalculator(cities: _*)(graph) match {
      case Some(distance) => distance.toString
      case _ => NoRouteMessage
    }

  def numberOfTripsBetweenCities(startCity: String, endCity: String, compareTo: (Int) => Boolean,
                                 maximumNumberOfStops: Int): String =
    NumberOfTripsCalculator(startCity, endCity, compareTo, maximumNumberOfStops)(graph) match {
      case Some(numberOfTrips) => numberOfTrips.toString
      case _ => NoRouteMessage
    }

  def shortestPathBetweenCities(startCity: String, endCity: String): String =
    ShortestPathCalculator(startCity, endCity)(graph) match {
      case Some(distance) => distance.toString
      case _ => NoRouteMessage
    }

}
