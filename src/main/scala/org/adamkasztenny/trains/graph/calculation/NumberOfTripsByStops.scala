package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph

object NumberOfTripsByStops extends TwoCityCalculation {

  def apply(startCity: String, endCity: String, predicate: (Int) => Boolean, maximumNumberOfStops: Int)
           (graph: TrainGraph): Option[Int] = {
    type Path = Seq[graph.NodeT]
    type Paths = Seq[Path]

    def traverseUntilMaximumNumberOfStops(start: graph.NodeT, end: graph.NodeT)
                (currentPath: Path = Seq.empty, allPaths: Paths = Seq.empty): Paths = {
      val newPath = currentPath :+ start

      if (newPath.length > maximumNumberOfStops + 1) return allPaths

      val pathIsValid = newPath.last == end
      val pathFulfillsPredicate = newPath.length > 1 && predicate(newPath.length - 1)
      if (pathIsValid && pathFulfillsPredicate) allPaths :+ newPath

      else {
        val successors = start.diSuccessors.toSeq
        successors.flatMap(successor => traverseUntilMaximumNumberOfStops(successor, end)(newPath))
      }
    }

    def calculateNumberOfPaths(start: graph.NodeT, end: graph.NodeT): Option[Int] = {
      val pathsFulfillingPredicate = traverseUntilMaximumNumberOfStops(start, end)()
      Option(pathsFulfillingPredicate.length)
    }

    calculateIfCitiesExist(startCity, endCity)(graph)(calculateNumberOfPaths)
  }
}
