package test.app.learning.play_json

import org.scalatest.FunSuite
import play.api.libs.json.{JsValue, Json}

class OnRenderSpec extends FunSuite {

  val givenJson: JsValue = Json.obj(
    "aname" -> "francisco",
    "onedate" -> "2019-02-20T23:20:36.642Z"
  )

  test("Compact") {
    assert(givenJson.toString() === """{"aname":"francisco","onedate":"2019-02-20T23:20:36.642Z"}""")
    assert(Json.stringify(givenJson) === """{"aname":"francisco","onedate":"2019-02-20T23:20:36.642Z"}""")
  }

  test("pretty print") {
    assert(Json.prettyPrint(givenJson) ===
      """{
        |  "aname" : "francisco",
        |  "onedate" : "2019-02-20T23:20:36.642Z"
        |}""".stripMargin)

  }
}
