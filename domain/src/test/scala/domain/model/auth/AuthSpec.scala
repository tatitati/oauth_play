package test.domain.model.auth

import org.scalatest.FunSuite
import test.domain.model.BuildScope
import test.domain.builders.BuildUuid

class AuthSpec extends FunSuite {

  test("I can compare two Auths by identity") {
    val givenAuth1 = BuildAuth.any(withId = BuildAuthId.specific1())
    val givenAuth2 = BuildAuth.any(withId = BuildAuthId.specific2())
    val givenAuth3 = BuildAuth.any(withId = BuildAuthId.specific2())

    assert(givenAuth1.equals(givenAuth2) === false)
    assert(givenAuth2.equals(givenAuth3) === true)
  }

  test("if live token, then the scope specify what can be accessed") {
    val givenAuth = BuildAuth.any(
      withScope = BuildScope.onlySurname(),
      withToken = BuildToken.anyLive()
    )

    assert(givenAuth.canReadFirstname === false)
    assert(givenAuth.canReadSurname === true)
  }

  test("If expired token, then nothing can be read") {
    val givenAuth = BuildAuth.any(
      withScope = BuildScope.onlySurname(),
      withToken = BuildToken.anyExpired()
    )

    assert(givenAuth.canReadFirstname === false)
    assert(givenAuth.canReadSurname === false)
  }

  test("Can refresh token if allowed") {
    val givenRefreshToken = BuildUuid.uuidOne()
    val givenAuth = BuildAuth.any(
      withToken = BuildToken.anyExpired(withRefreshToken = givenRefreshToken),
      withScope = BuildScope.onlyEmailAndFirstname()
    )

    assert(givenAuth.getToken.isLive === false)

    val originalToken = givenAuth.getToken
    val wasRefreshed = givenAuth.refreshToken(givenRefreshToken, "refresh_token")
    val newtoken = givenAuth.getToken

    assert(givenAuth.getToken.isLive === true)
    assert(originalToken.equals(newtoken) === false)
  }
}
