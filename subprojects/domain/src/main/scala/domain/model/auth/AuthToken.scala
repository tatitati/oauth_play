package domain.model.auth

import java.util.UUID
import com.github.nscala_time.time.Imports._

case class AuthToken(
             accessToken: UUID,
             tokenType: String = "bearer",
             refreshToken: UUID,
             expiresIn: Int = 360000,
             generatedIn: DateTime
  ) {

  def isLive: Boolean = {
    val expireInDate = generatedIn + expiresIn.seconds
    expireInDate >= DateTime.now()
  }

  def canBeRefreshed(withRefreshToken: UUID, grantType: String): Boolean = {
    grantType match {
      case "refresh_token" if !isLive => refreshToken.equals(withRefreshToken)
      case _ => false
    }
  }
}
