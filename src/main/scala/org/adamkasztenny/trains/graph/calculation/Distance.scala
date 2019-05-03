package org.adamkasztenny.trains.graph.calculation

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph
import org.adamkasztenny.trains.graph.calculation.conversions.DoubleConversions._
import org.adamkasztenny.trains.graph.calculation.conversions.RichOption._

object Distance {

  def apply(cities: String*)(graph: TrainGraph): Option[Int] = {
    def traverseGraph(nodes: Seq[graph.NodeT]): graph.Path = {
      val startNode = nodes.head
      val builder = graph.newPathBuilder(startNode)
      nodes.tail.foreach(node => builder += node)
      builder.result()
    }

    def calculateDistance(nodes: Seq[graph.NodeT]): Option[Int] = {
      val path = traverseGraph(nodes)
      val pathIsIncomplete = path.nodes != nodes
      if (pathIsIncomplete) return None
      Option(path.weight).nonZero
    }

    val possibleCityNodes = cities.flatMap(city => graph.find(city))
    possibleCityNodes match {
      case nodes: Seq[graph.NodeT] if nodes.nonEmpty => calculateDistance(nodes)
      case _ => None
    }
  }
}
