package org.adamkasztenny.trains

import org.scalatest.{FunSuite, Matchers}
import Conversions._

class ConversionsUnitTest extends FunSuite with Matchers {

  test("should convert a Double to an Int") {
    doubleToInt(0.0) shouldBe 0
    doubleToInt(3.0) shouldBe 3
  }

  test("should convert an Option of a Double to an Option of an Int, if one is defined") {
    optionDoubleToInt(Option(0.0)) shouldBe Option(0)
    optionDoubleToInt(Option(3.0)) shouldBe Option(3)
  }

  test("should preserve None if the Double is None") {
    optionDoubleToInt(None) shouldBe None
  }
}
