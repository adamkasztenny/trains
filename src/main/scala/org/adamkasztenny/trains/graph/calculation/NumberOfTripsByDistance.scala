package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph
import org.adamkasztenny.trains.graph.calculation.conversions.DoubleConversions._

object NumberOfTripsByDistance extends TwoCityCalculation {

  def apply(startCity: String, endCity: String, maximumDistance: Int)
           (graph: TrainGraph): Option[Int] = {
    case class Path(nodes: Seq[graph.NodeT], distance: Int)
    type Paths = Seq[Path]

    def updatePathWithDistance(start: graph.NodeT, currentPath: Path): Path = {
      val newPath = currentPath.copy(nodes = currentPath.nodes :+ start)
      val builder = graph.newWalkBuilder(newPath.nodes.head)
      newPath.nodes.tail.foreach(node => builder += node)
      val distanceSoFar = builder.result().weight
      newPath.copy(distance = distanceSoFar)
    }

    def accumulatePath(paths: Paths, path: Path): Paths = {
      val isWithinDistance = path.distance < maximumDistance
      if (isWithinDistance) paths :+ path
      else paths
    }

    def traverseUntilMaximumDistance(start: graph.NodeT)
                                    (currentPath: Path = Path(Seq.empty, 0), allPaths: Paths = Seq.empty): Paths = {
      val newPath = updatePathWithDistance(start, currentPath)
      if (newPath.distance > maximumDistance) return allPaths

      val accumulatedPaths = accumulatePath(allPaths, newPath)

      val successors = start.diSuccessors.toSeq
      if (successors.isEmpty) accumulatedPaths
      else successors.flatMap(successor => traverseUntilMaximumDistance(successor)(newPath, accumulatedPaths))
    }

    def calculateNumberOfPaths(start: graph.NodeT, end: graph.NodeT): Option[Int] = {
      val allPaths = traverseUntilMaximumDistance(start)()
      val distinctPaths = allPaths.distinct
      val nonEmptyPaths = distinctPaths.filterNot(_.distance == 0)
      val validPaths = nonEmptyPaths.filter(_.nodes.last == end)
      val pathsWithinDistance = validPaths.filter(_.distance < maximumDistance)
      Option(pathsWithinDistance.length)
    }

     calculateIfCitiesExist(startCity, endCity)(graph)(calculateNumberOfPaths)
  }
}
