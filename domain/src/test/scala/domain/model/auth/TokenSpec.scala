package test.domain.model.auth

import org.scalatest.FunSuite
import test.domain.builders.BuildUuid

class TokenSpec extends FunSuite{

  test("Know if a token is live") {
    val givenLiveToken = BuildToken.anyLive()
    val givenExpiredToken = BuildToken.anyExpired()

    assert(givenLiveToken.isLive === true)
    assert(givenExpiredToken.isLive === false)
  }

  test("Cannot refresh if is still live") {
    val refreshToken = BuildUuid.uuidOne()
    val liveToken = BuildToken.anyLive(withRefreshToken = refreshToken)

    assert(liveToken.canBeRefreshed(refreshToken, "refresh_token") === false)
  }

  test("Cannot refresh if used wrong GRANT TYPE") {
    val refreshToken = BuildUuid.uuidOne()
    val expiredToken = BuildToken.anyExpired(withRefreshToken = refreshToken)

    assert(expiredToken.canBeRefreshed(refreshToken, "wrong_gran_type") === false)
  }

  test("Cannot refresh if used wrong refresh_token") {
    val refreshToken = BuildUuid.uuidOne()
    val wrongRefreshToken = BuildUuid.uuidTwo()
    val expiredToken = BuildToken.anyExpired(withRefreshToken = refreshToken)

    assert(expiredToken.canBeRefreshed(wrongRefreshToken, "refresh_token") === false)
  }

  test("Can refresh if is expired and is used the correct refresh_token") {
    val rightRefreshToken = BuildUuid.uuidOne()
    val wrongRefreshToken = BuildUuid.uuidTwo()
    val expiredToken = BuildToken.anyExpired(
      withRefreshToken = rightRefreshToken
    )

    assert(expiredToken.canBeRefreshed(rightRefreshToken, "refresh_token") === true)
  }
}
