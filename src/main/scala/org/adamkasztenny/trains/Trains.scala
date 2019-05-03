package org.adamkasztenny.trains

import org.adamkasztenny.trains.api.TrainCalculationApi
import org.adamkasztenny.trains.graph.TrainGraphBuilder
import org.adamkasztenny.trains.graph.TrainGraphTypes.TrainGraph

object Trains extends App {

  override def main(args: Array[String]): Unit = {
    val graph = TrainGraphBuilder(args)
    printOutput(graph)
  }

  private def printOutput(graph: TrainGraph): Unit = {
    val trainCalculationApi = new TrainCalculationApi(graph)
    println(s"Output #1: ${trainCalculationApi.distanceBetweenCities("A", "B", "C")}")
    println(s"Output #2: ${trainCalculationApi.distanceBetweenCities("A", "D")}")
    println(s"Output #3: ${trainCalculationApi.distanceBetweenCities("A", "D", "C")}")
    println(s"Output #4: ${trainCalculationApi.distanceBetweenCities("A", "E", "B", "C", "D")}")
    println(s"Output #5: ${trainCalculationApi.distanceBetweenCities("A", "E", "D")}")

    println(s"Output #6: ${trainCalculationApi.numberOfTripsBetweenCitiesByStops("C", "C", _ <= 3, 3)}")
    println(s"Output #7: ${trainCalculationApi.numberOfTripsBetweenCitiesByStops("A", "C", _ == 4, 4)}")

    println(s"Output #8: ${trainCalculationApi.shortestPathBetweenCities("A", "C")}")
    println(s"Output #9: ${trainCalculationApi.shortestPathBetweenCities("B", "B")}")

    println(s"Output #10: ${trainCalculationApi.numberOfTripsBetweenCitiesByDistance("C", "C", 30)}")
  }
}
