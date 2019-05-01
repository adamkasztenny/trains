package org.adamkasztenny.trains.graph

import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph

import scalax.collection.edge.WkDiEdge
import scalax.collection.immutable.Graph

object SampleGraph {
  def apply(): TrainGraph =
    Graph(
      WkDiEdge("A", "B")(5),
      WkDiEdge("B", "C")(4),
      WkDiEdge("C", "D")(8),
      WkDiEdge("D", "C")(8),
      WkDiEdge("D", "E")(6),
      WkDiEdge("A", "D")(5),
      WkDiEdge("C", "E")(2),
      WkDiEdge("E", "B")(3),
      WkDiEdge("A", "E")(7)
    )
}
