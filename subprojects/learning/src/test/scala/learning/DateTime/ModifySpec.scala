package test.app.learning.DateTime

import com.github.nscala_time.time.Imports._
import org.scalatest.FunSuite

class ModifySpec extends FunSuite {

  test("Compare two dates") {
    val now = DateTime.now()
    val dateFuture = DateTime.now() + 2.months

    assert(now < dateFuture)
  }
}
