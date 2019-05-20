package test.app.learning.play_json

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.scalatest.FunSuite
import play.api.libs.json._
import play.api.libs.json.{JsValue, Json}
import play.api.libs.functional.syntax._

class OnParseSpec extends FunSuite {

  case class GivenCaseClassWithDate(val firstName: String, val mydatetime: DateTime)
  class GivenClassWithDate(val firstName: String, val mydatetime: DateTime)

  test("Parse return a JsValue") {
    val json: JsValue = Json.parse("""
      {
        "firstName": "francisco",
        "mydatetime": "2030-02-20T13:08:20.020Z"
      }
      """)

    assert(json.isInstanceOf[JsValue])
  }

  test("Extracted values are also JsValues") {
    val json: JsValue = Json.parse("""
      {
        "firstName": "francisco",
        "mydatetime": "2030-02-20T13:08:20.020Z"
      }
      """)

    val extracted = (json \ "mydatetime").get

    assert(extracted.isInstanceOf[JsValue])
  }

  test("I can extract the raw value of a JsValue") {
    val json: JsValue = Json.parse("""
      {
        "firstName": "francisco",
        "mydatetime": "2030-02-20T13:08:20.020Z"
      }
      """)

    val jodaDateReads = Reads[DateTime](js =>
      js.validate[String].map[DateTime](dtString =>
        DateTime.parse(dtString, DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        ))
      )
    )

    val extracted = (json \ "mydatetime").get

    assert(extracted.toString() === "\"2030-02-20T13:08:20.020Z\"")
    assert((json \ "mydatetime").as[String] === "2030-02-20T13:08:20.020Z")
    assert(json("mydatetime").as[String] === "2030-02-20T13:08:20.020Z")
    assert(json("mydatetime").as[DateTime](jodaDateReads) === new DateTime("2030-02-20T13:08:20.020Z"))
  }

  test("WITH CASE CLASSES: I can parse an string datetime into a DateTime object") {
    val json: JsValue = Json.parse("""
      {
        "firstName": "francisco",
        "mydatetime": "2030-02-20T13:08:20.020Z"
      }
      """)

    val jodaDateReads = Reads[DateTime](js =>
      js.validate[String].map[DateTime](dtString =>
        DateTime.parse(dtString, DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        ))
      )
    )

    val givenCaseClassReads: Reads[GivenCaseClassWithDate] = (
          (JsPath \ "firstName").read[String] and
          (JsPath \ "mydatetime").read[DateTime](jodaDateReads)
      )(GivenCaseClassWithDate.apply _)


    val parsed = json.validate[GivenCaseClassWithDate](givenCaseClassReads)
    val fetched = parsed.getOrElse("Undefined")

    assert(parsed === JsSuccess(GivenCaseClassWithDate("francisco", new DateTime("2030-02-20T13:08:20.020Z"))))
    assert(fetched === GivenCaseClassWithDate("francisco", new DateTime("2030-02-20T13:08:20.020Z")))
  }

  test("WITH NORMAL CLASSES: I can parse an string datetime into a DateTime object") {
    val json: JsValue = Json.parse("""
      {
        "firstName": "francisco",
        "mydatetime": "2030-02-20T13:08:20.020Z"
      }
      """)

    val jodaDateReads = Reads[DateTime](js =>
        js.validate[String].map[DateTime](dtString =>
        DateTime.parse(dtString, DateTimeFormat.forPattern( "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
        ))
      )
    )

    val givenClassReads: Reads[GivenClassWithDate] = (
        (JsPath \ "firstName").read[String] and
        (JsPath \ "mydatetime").read[DateTime](jodaDateReads)
      )((a, b) => new GivenClassWithDate(a, b))


    val parsed = json.validate[GivenClassWithDate](givenClassReads)
    val fetched = parsed.get

    val expected = new GivenClassWithDate("francisco", new DateTime("2030-02-20T13:08:20.020Z"))

    assert(parsed.isInstanceOf[JsSuccess[GivenClassWithDate]] === true)
    assert(fetched.firstName === "francisco")
    assert(fetched.mydatetime === new DateTime("2030-02-20T13:08:20.020Z"))

  }
}
