package org.adamkasztenny.trains.graph.calculation

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

object DistanceCalculator {

  def apply(cities: String*)(graph: Graph[String, WkDiEdge]): Option[Int] = {
    def calculateDistance(nodes: Seq[graph.NodeT]): Option[Int] = {
      val startNode = nodes.head
      val builder = graph.newPathBuilder(startNode)
      nodes.tail.foreach(node => builder += node)
      Option(builder.result.weight.toInt).filterNot(_ == 0)
    }

    val possibleCityNodes = cities.flatMap(city => graph.find(city))
    possibleCityNodes match {
      case nodes: Seq[graph.NodeT] if nodes.nonEmpty => calculateDistance(nodes)
      case _ => None
    }
  }
}
