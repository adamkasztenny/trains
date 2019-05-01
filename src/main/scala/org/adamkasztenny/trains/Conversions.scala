package org.adamkasztenny.trains

import scala.languageFeature.implicitConversions

object Conversions {
  implicit def doubleToInt(double: Double): Int = double.toInt
  implicit def optionDoubleToInt(double: Option[Double]): Option[Int] = double.map(_.toInt)
}
