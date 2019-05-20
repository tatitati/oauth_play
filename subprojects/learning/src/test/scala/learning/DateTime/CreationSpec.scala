package test.app.learning.DateTime

import com.github.nscala_time.time.Imports._
import org.scalatest.FunSuite

class CreationSpec extends FunSuite {

  test("Create a date") {
    val date = DateTime.now()
    assert(date.isInstanceOf[DateTime])
  }

  test("Create it using the constructor") {
    val givenDate = new DateTime("2030-02-20T13:08:20.020Z")

    val expectedDate = new DateTime()
      .withDate(2030, 2, 20)
      .withTime(13, 8, 20, 20)

    assert(givenDate === expectedDate)
  }

  test("Create using fluent methods") {
    val specificdate1 = new DateTime()
      .withYear(2030)
      .withMonth(8)
      .withDay(20)
      .withZone(DateTimeZone.UTC)

    val specificdate2 = new DateTime()
      .withDate(2030, 8, 20)
      .withTime(13, 8, 20, 400)
      .withZone(DateTimeZone.UTC)

    assert(specificdate1.getYear === 2030)
    assert(specificdate2.getMonthOfYear === 8)
  }
}
