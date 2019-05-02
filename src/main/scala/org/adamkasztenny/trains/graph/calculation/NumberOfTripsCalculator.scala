package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph

object NumberOfTripsCalculator {

  def apply(startCity: String, endCity: String, compareTo: (Int) => Boolean, maximumNumberOfStops: Int)
           (graph: TrainGraph): Option[Int] = {
    type Path = Seq[graph.NodeT]
    type Paths = Seq[Path]

    def traverse(start: graph.NodeT, end: graph.NodeT)
                (currentPath: Path = Seq.empty, allPaths: Paths = Seq.empty): Paths = {
      val newPath = currentPath :+ start

      if (newPath.length > maximumNumberOfStops + 1) return allPaths

      val pathIsValid = newPath.last == end
      val pathFulfillsPredicate = newPath.length > 1 && compareTo(newPath.length - 1)
      if (pathIsValid && pathFulfillsPredicate) allPaths :+ newPath

      else {
        val successors = start.diSuccessors.toSeq
        successors.flatMap(successor => traverse(successor, end)(newPath))
      }
    }

    def calculateNumberOfPaths(start: graph.NodeT, end: graph.NodeT): Option[Int] = {
      val pathsFulfillingPredicate = traverse(start, end)()
      Option(pathsFulfillingPredicate.length).filterNot(_ == 0)
    }

    (graph.find(startCity), graph.find(endCity)) match {
      case (Some(start), Some(end)) => calculateNumberOfPaths(start, end)
      case _ => None
    }
  }
}
