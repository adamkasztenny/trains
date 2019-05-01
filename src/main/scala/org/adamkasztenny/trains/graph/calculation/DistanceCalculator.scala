package org.adamkasztenny.trains.graph.calculation

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

object DistanceCalculator {

  def apply(cities: String*)(graph: Graph[String, WkDiEdge]): Option[Int] = {
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
      Option(path.weight.toInt).filterNot(_ == 0)
    }

    val possibleCityNodes = cities.flatMap(city => graph.find(city))
    possibleCityNodes match {
      case nodes: Seq[graph.NodeT] if nodes.nonEmpty => calculateDistance(nodes)
      case _ => None
    }
  }
}
