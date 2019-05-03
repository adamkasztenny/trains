package org.adamkasztenny.trains.graph.calculation.conversions

import scala.languageFeature.implicitConversions

private[calculation] class RichOption[T](option: Option[T]) {
  def nonZero: Option[T] = option.filterNot(_ == 0)
}

private[calculation] object RichOption {
  implicit def optionToRichOption[T](option: Option[T]): RichOption[T] = new RichOption[T](option)
}