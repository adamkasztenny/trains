package org.adamkasztenny.trains.graph.calculation.conversions

import scala.languageFeature.implicitConversions

object DoubleConversions {
  implicit def doubleToInt(double: Double): Int = double.toInt
  implicit def optionDoubleToInt(double: Option[Double]): Option[Int] = double.map(_.toInt)
}
