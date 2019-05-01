package org.adamkasztenny.trains

import org.adamkasztenny.trains.graph.calculation.DistanceCalculator

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

class TrainCalculationApi(graph: Graph[String, WkDiEdge]) {

  def distanceBetweenCities(cities: String*): String =
    DistanceCalculator(cities: _*)(graph) match {
      case Some(distance) => distance.toString
      case _ => "NO SUCH ROUTE"
    }
}
