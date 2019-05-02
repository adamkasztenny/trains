package org.adamkasztenny.trains.graph.calculation

import scala.languageFeature.implicitConversions

class RichOption[T](option: Option[T]) {
  def nonZero: Option[T] = option.filterNot(_ == 0)
}

object RichOption {
  implicit def optionToRichOption[T](option: Option[T]): RichOption[T] = new RichOption[T](option)
}