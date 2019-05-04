package org.adamkasztenny.trains.graph.calculation.conversions

import org.adamkasztenny.trains.graph.calculation.conversions.RichOption._
import org.scalatest.{FunSuite, Matchers, OptionValues}

class RichOptionUnitTest extends FunSuite with Matchers with OptionValues {

  test("should return None if the value is 0") {
    Option(0).nonZero shouldBe None
    Some(0).nonZero shouldBe None
  }

  test("should return an Option of the value if it is not 0") {
    Option(3).nonZero.value shouldBe 3
    Some(5).nonZero.value shouldBe 5
  }

  test("should preserve None") {
    None.nonZero shouldBe None
  }
}
