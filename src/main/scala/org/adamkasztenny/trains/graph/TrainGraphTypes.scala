package org.adamkasztenny.trains.graph

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

object TrainGraphTypes {
  type TrainGraph = Graph[String, WkDiEdge]
}
