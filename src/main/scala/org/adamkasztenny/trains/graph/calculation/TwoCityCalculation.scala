package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.TrainGraphTypes._

private[calculation] trait TwoCityCalculation {

  def calculateIfCitiesExist[T](startCity: String, endCity: String)(graph: TrainGraph)
                            (calculation: (graph.NodeT, graph.NodeT) => Option[Int]): Option[Int] = {
    (graph.find(startCity), graph.find(endCity)) match {
      case (Some(start), Some(end)) => calculation(start, end)
      case _ => None
    }
  }
}
