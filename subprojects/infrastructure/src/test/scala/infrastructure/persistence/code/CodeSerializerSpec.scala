package infrastructure.test.persistence.code

import infrastructure.persistence.code.CodeSerializer
import org.scalatest.FunSuite
import test.domain.model.code.BuildCode

class CodeSerializerSpec extends FunSuite {

  test("Code -> Json") {
    val givenCode = BuildCode.specific()

    val thenJson = CodeSerializer.toJson(givenCode)

    assert(thenJson ===
      """{
        |"id":"47331985-9cd6-4632-b505-fceb476100a1",
        |"user_id":"user id one",
        |"site_id":"47331985-9cd6-4632-b505-fceb476100a1",
        |"state":"any state",
        |"scope":{
            |"firstname":true,
            |"surname":false,
            |"email":true
          |}
        |}""".stripMargin.replaceAll("\n", ""))
  }

  test("Json -> Code") {

    val givenJson =
      """{
          |"id":"47331985-9cd6-4632-b505-fceb476100a1",
          |"user_id":"user id one",
          |"site_id":"47331985-9cd6-4632-b505-fceb476100a1",
          |"state":"any state",
          |"scope":{
            |"firstname":true,
            |"surname":false,
            |"email":true
          |}
        |}""".stripMargin.replaceAll("\n", "")


    val codeMapped = CodeSerializer.toDomain(givenJson)

    assert(BuildCode.specific().equals(codeMapped))
  }
}
