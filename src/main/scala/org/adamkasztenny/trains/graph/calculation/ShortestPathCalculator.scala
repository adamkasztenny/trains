package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.TrainGraphTypes._

object ShortestPathCalculator {

  def apply(startCity: String, endCity: String)(graph: TrainGraph): Option[Int] = {
    val startNode = graph.find(startCity)
    val endNode = graph.find(endCity)

    def calculateShortestPathToSelf(node: graph.NodeT): Option[Int] = {
      val successors = node.diSuccessors
      val pathsBackToStart = successors.map(successor => successor shortestPathTo node).filter(_.isDefined).map(_.get)
      val shortestPathBackToStart = pathsBackToStart.minBy(_.weight)
      (node shortestPathTo shortestPathBackToStart.nodes.head).map(_.weight + shortestPathBackToStart.weight)
        .map(_.toInt)
    }

    def calculateShortestPath(start: graph.NodeT, end: graph.NodeT): Option[Int] = {
      if (start == end) calculateShortestPathToSelf(start)
      else (start shortestPathTo end).map(_.weight.toInt)
    }

    (startNode, endNode) match {
      case (Some(start), Some(end)) => calculateShortestPath(start, end)
      case _ => None
    }
  }
}
