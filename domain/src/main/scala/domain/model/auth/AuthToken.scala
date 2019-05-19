package domain.model.auth

import java.util.UUID
import com.github.nscala_time.time.Imports._

case class AuthToken(
             val accessToken: UUID,
             val tokenType: String = "bearer",
             val refreshToken: UUID,
             val expiresIn: Int = 360000,
             val generatedIn: DateTime
  ) {

  def isLive: Boolean = {
    val expireInDate = generatedIn + expiresIn.seconds
    val now = DateTime.now()
    expireInDate >= now
  }

  def canBeRefreshed(withRefreshToken: UUID, grantType: String): Boolean = {
    grantType match {
      case "refresh_token" if !isLive => refreshToken.equals(withRefreshToken)
      case _ => false
    }
  }
}
