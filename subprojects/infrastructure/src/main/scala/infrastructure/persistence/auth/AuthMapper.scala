package infrastructure.persistence.auth

import java.util.UUID
import domain.model.Scope
import domain.model.auth.{Auth, AuthId, AuthToken}
import domain.model.thirdapp.ThirdappId
import domain.model.user.UserId

object AuthMapper {

  def toPersistent(auth: Auth): AuthPersistentModel = {
    AuthPersistentModel(
      surrogateId = auth.getSurrogateId(),
      id = auth.authId.toString,
      thirdId = auth.thirdappId.toString(),
      userId = auth.userId.toString(),
      scopeFirstName = auth.scope.firstname,
      scopeSurname = auth.scope.surname,
      scopeEmail = auth.scope.email,
      tokenAccess = auth.getToken.accessToken.toString,
      tokenRefresh = auth.getToken.refreshToken.toString,
      tokenExpiresIn = auth.getToken.expiresIn,
      tokenGeneratedIn = auth.getToken.generatedIn,
      tokenType = auth.getToken.tokenType
    )
  }

  def toDomain(persistent: AuthPersistentModel): Auth = {
    val auth = new Auth(
      authId = AuthId(UUID.fromString(persistent.id)),
      thirdappId = ThirdappId(UUID.fromString(persistent.thirdId)),
      userId = UserId(persistent.userId),
      scope = Scope(
        firstname = persistent.scopeFirstName,
        surname = persistent.scopeSurname,
        email = persistent.scopeEmail
      ),
      token = AuthToken(
        accessToken = UUID.fromString(persistent.tokenAccess),
        refreshToken = UUID.fromString(persistent.tokenRefresh),
        tokenType = persistent.tokenType,
        expiresIn = persistent.tokenExpiresIn,
        generatedIn = persistent.tokenGeneratedIn
      )
    )

    if(persistent.surrogateId != None) {
      auth.setSurrogateId(persistent.surrogateId)
    }

    auth
  }
}
