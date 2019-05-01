package org.adamkasztenny.trains.graph

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

object TrainGraphBuilder {

  def apply(textEdges: Array[String]): Graph[String, WkDiEdge] = {
    val edges = textEdges.map(textEdge => {
      val startNode = textEdge.head.toString
      val endNode = textEdge.charAt(1).toString
      val weight = textEdge.last.toString.toDouble
      WkDiEdge(startNode, endNode)(weight)
    })
    Graph(edges: _*)
  }
}
