package org.adamkasztenny.trains

import org.adamkasztenny.trains.graph.calculation.DistanceCalculator

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class TrainCalculationApi(graph: Graph[String, WkDiEdge]) {

  def distanceBetweenCities(startCity: String, endCity: String): String =
    DistanceCalculator(startCity, endCity)(graph) match {
      case Some(distance) => distance.toString
      case _ => "NO SUCH ROUTE"
    }
}
