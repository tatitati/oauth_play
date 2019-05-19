package infrastructure.test.persistence.code

import infrastructure.persistence.code.CodeSerializer
import org.scalatest.FunSuite
import test.domain.model.code.BuildCode

class CodeSerializerSpec extends FunSuite {

  test("Can serialize an specific object") {
    val resByCode = BuildCode.specific()

    val inJson = CodeSerializer.toJson(resByCode)

    assert(inJson ===
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

  test("Can deserialize an specific json") {

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
