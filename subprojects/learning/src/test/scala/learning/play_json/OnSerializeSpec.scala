package test.app.learning.play_json

import org.joda.time.DateTime
import org.scalatest.FunSuite
import play.api.libs.json._

class OnSerializeSpec extends FunSuite {
  class GivenClassWithDate(val firstName: String, val mydatetime: DateTime)

  test("I can pass to json using custom writes") {
    val sample = new GivenClassWithDate(firstName = "francisco", mydatetime = new DateTime("2019-02-20T23:20:36.642Z"))


    implicit val givenClassWrites = new Writes[GivenClassWithDate] {
      def writes(givenClass: GivenClassWithDate) = Json.obj(
        "aname" -> givenClass.firstName,
        "onedate" -> givenClass.mydatetime.toString
      )
    }

    val inJson = Json.toJson(sample)

    assert(inJson.isInstanceOf[JsValue])
    assert(inJson.toString() === """{"aname":"francisco","onedate":"2019-02-20T23:20:36.642Z"}""")
  }

  test("I can pass to json using DSL") {
    val sample = new GivenClassWithDate(firstName = "francisco", mydatetime = new DateTime("2019-02-20T23:20:36.642Z"))


    val inJson: JsValue = Json.obj(
      "aname" -> sample.firstName,
      "onedate" -> sample.mydatetime.toString()
    )

    assert(inJson.isInstanceOf[JsValue])
    assert(inJson.toString() === """{"aname":"francisco","onedate":"2019-02-20T23:20:36.642Z"}""")
  }
}
