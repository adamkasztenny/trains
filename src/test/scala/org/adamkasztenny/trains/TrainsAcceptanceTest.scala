package org.adamkasztenny.trains

import java.io.ByteArrayOutputStream

import org.scalatest.{BeforeAndAfterEach, FunSuite, Matchers}

/**
  * This is a high-level acceptance test that checks if the entire program works as expected.
  * All the computations passed to the println statements in Trains are tested in the unit tests for the
  * respective calculations.
  *
  * Thanks to https://www.scala-lang.org/api/current/scala/Console$.html for showing me the neat Console.withOut
  * function.
  *
  * This test is excluded from coverage, thanks to https://github.com/scoverage/sbt-scoverage/issues/225.
  **/

class TrainsAcceptanceTest extends FunSuite with Matchers with BeforeAndAfterEach {

  test("should output the calculation results as per the sample") {
    val output = new ByteArrayOutputStream

    Console.withOut(output) {
      Trains.main(Array("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"))
    }

    output.toString.trim shouldBe
      """|Output #1: 9
        |Output #2: 5
        |Output #3: 13
        |Output #4: 22
        |Output #5: NO SUCH ROUTE
        |Output #6: 2
        |Output #7: 3
        |Output #8: 9
        |Output #9: 9
        |Output #10: 7""".stripMargin
  }

  test("should work with an empty graph") {
    val output = new ByteArrayOutputStream

    Console.withOut(output) {
      Trains.main(Array.empty)
    }

    output.toString.trim shouldBe
      """|Output #1: NO SUCH ROUTE
        |Output #2: NO SUCH ROUTE
        |Output #3: NO SUCH ROUTE
        |Output #4: NO SUCH ROUTE
        |Output #5: NO SUCH ROUTE
        |Output #6: NO SUCH ROUTE
        |Output #7: NO SUCH ROUTE
        |Output #8: NO SUCH ROUTE
        |Output #9: NO SUCH ROUTE
        |Output #10: NO SUCH ROUTE""".stripMargin
  }

  test("should work with larger distances") {
    val output = new ByteArrayOutputStream

    Console.withOut(output) {
      Trains.main(Array("AB50", "BC40", "CD80", "DC80", "DE60", "AD50", "CE20", "EB30", "AE70"))
    }

    output.toString.trim shouldBe
      """|Output #1: 90
        |Output #2: 50
        |Output #3: 130
        |Output #4: 220
        |Output #5: NO SUCH ROUTE
        |Output #6: 2
        |Output #7: 3
        |Output #8: 90
        |Output #9: 90
        |Output #10: 0""".stripMargin
  }
}