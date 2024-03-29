package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.TrainGraphTypes._
import org.adamkasztenny.trains.graph.calculation.conversions.DoubleConversions._

object ShortestPath extends TwoCityCalculation {

  def apply(startCity: String, endCity: String)(graph: TrainGraph): Option[Int] = {

    def calculateShortestPathToSelf(node: graph.NodeT): Option[Int] = {
      val successors = node.diSuccessors
      val pathsBackToStart = successors.map(successor => successor shortestPathTo node).filter(_.isDefined).map(_.get)
      if (pathsBackToStart.isEmpty) None
      else {
        val lengthOfShortestPathBackToStart = pathsBackToStart.map(path => {
          val distanceFromStart = (node shortestPathTo path.nodes.head).get.weight
          distanceFromStart + path.weight
        }).min
        Option(lengthOfShortestPathBackToStart)
      }
    }

    def calculateShortestPath(start: graph.NodeT, end: graph.NodeT): Option[Int] = {
      if (start == end) calculateShortestPathToSelf(start)
      else (start shortestPathTo end).map(_.weight)
    }

    calculateIfCitiesExist(startCity, endCity)(graph)(calculateShortestPath)
  }
}
