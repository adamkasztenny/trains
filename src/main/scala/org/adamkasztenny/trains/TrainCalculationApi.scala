package org.adamkasztenny.trains

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph
import org.adamkasztenny.trains.graph.calculation.DistanceCalculator

class TrainCalculationApi(graph: TrainGraph) {

  def distanceBetweenCities(cities: String*): String =
    DistanceCalculator(cities: _*)(graph) match {
      case Some(distance) => distance.toString
      case _ => "NO SUCH ROUTE"
    }
}
