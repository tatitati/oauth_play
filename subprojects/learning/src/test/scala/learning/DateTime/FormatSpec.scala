package test.app.learning.DateTime

import com.github.nscala_time.time.Imports._
import org.scalatest.FunSuite

class FormatSpec extends FunSuite {

  test("Format an string in an specific format") {

    val specificdate = new DateTime()
      .withDate(2030, 2, 20)
      .withTime(13, 8, 20, 20)

    assert(specificdate.toString === "2030-02-20T13:08:20.020Z")
    assert(specificdate.toString("Y-M--d") === "2030-2--20")
    assert(specificdate.toString("Y-MM-dd H:mm:s") === "2030-02-20 13:08:20")

  }
}
