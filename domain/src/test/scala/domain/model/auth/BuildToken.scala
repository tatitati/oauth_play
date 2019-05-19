package test.domain.model.auth

import java.util.UUID

import domain.model.auth.AuthToken
import com.github.nscala_time.time.Imports._
import test.domain.builders.{BuildUuid, Faker}

object BuildToken {
  def anyLive(
               withTokenType: String = "bearer",
               withExpirationIn: Int = 10,
               withRefreshToken: UUID = java.util.UUID.randomUUID,
               withAccessToken: UUID = java.util.UUID.randomUUID
             ): AuthToken = {
    new AuthToken(
      accessToken = withAccessToken,
      tokenType = withTokenType,
      refreshToken = withRefreshToken,
      generatedIn = DateTime.now() - 5.seconds,
      expiresIn = withExpirationIn
    )
  }

  def anyExpired(
                  withTokenType: String = "bearer",
                  withExpirationIn: Int = 10,
                  withRefreshToken: UUID = java.util.UUID.randomUUID,
                  withAccessToken: UUID = java.util.UUID.randomUUID
                ): AuthToken = {
    new AuthToken(
      accessToken = withAccessToken,
      tokenType = withTokenType,
      refreshToken = withRefreshToken,
      generatedIn = DateTime.now() - 20.seconds,
      expiresIn = withExpirationIn
    )
  }

  def specific(): AuthToken = {
    new AuthToken(
      tokenType = "bearer",
      accessToken = BuildUuid.uuidOne(),
      refreshToken = BuildUuid.uuidTwo(),
      generatedIn = DateTime.now() - 5.seconds,
      expiresIn = 10
    )
  }

  def any(): AuthToken = {
    Faker(anyLive(), anyExpired())
  }
}
