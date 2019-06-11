package test.domain.services

import domain.model.auth.Auth
import domain.services.TranslatorCodeToAuth
import org.scalatest.FunSuite
import test.domain.model.code.BuildCode

class TranslatorCodeToAuthSpec extends FunSuite {

  test("A code is converted into an auth") {
    val givenCode = BuildCode.any()

    val auth = TranslatorCodeToAuth.toAuth(givenCode)

    assert(auth.isInstanceOf[Auth])
  }
}
