package org.adamkasztenny.trains.graph.calculation.conversions

import scala.languageFeature.implicitConversions

private[calculation] object DoubleConversions {
  implicit def doubleToInt(double: Double): Int = double.toInt

  implicit def optionOfDoubleToOptionOfInt(maybeDouble: Option[Double]): Option[Int] = maybeDouble.map(_.toInt)
}
