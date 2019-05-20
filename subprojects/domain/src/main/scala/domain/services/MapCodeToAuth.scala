package domain.services

import java.util.UUID

import com.github.nscala_time.time.Imports._
import domain.model.code.Code
import domain.model.thirdapp.ThirdappId
import domain.model.auth.{Auth, AuthId, AuthToken}
import domain.model.user.UserId

object MapCodeToAuth {
  def toAuth(code: Code): Auth = {
    new Auth(
      authId = AuthId(UUID.randomUUID()),
      thirdappId = ThirdappId(code.thirdappId.value),
      userId = UserId(code.userId.value),
      scope = code.scope,
      token = generateToken()
    )
  }

  private def generateToken(): AuthToken = {
    AuthToken(
      accessToken = UUID.randomUUID(),
      refreshToken = UUID.randomUUID(),
      generatedIn = DateTime.now(),
      tokenType = "bearer",
      expiresIn = 36000
    )
  }
}
