package infrastructure.test.persistence.auth

import com.github.nscala_time.time.Imports.DateTime
import infrastructure.persistence.auth.AuthPersistentModel
import test.domain.builders.{BuildDate, BuildUuid, Faker}

object BuildAuthPersistentModel {
  def any(
           withSurrogateId: Option[Long]= None,
           withId: String = BuildUuid.any().toString,
           withThirdId: String = BuildUuid.any().toString,
           withUserId: String = Faker.text(),
           withScopeFirstName: Boolean = Faker.boolean(),
           withScopeSurname: Boolean = Faker.boolean(),
           withScopeEmail: Boolean = true,
           withTokenType: String = Faker.text(),
           withTokenAccess: String = BuildUuid.any().toString,
           withTokenRefresh: String = BuildUuid.any().toString,
           withTokenExpiresIn: Int = Faker.int(),
           withTokenGeneratedIn: DateTime = BuildDate.any()
   ): AuthPersistentModel = {

      AuthPersistentModel(
          surrogateId = withSurrogateId,
          id = withId,
          thirdId = withThirdId,
          userId = withUserId,
          scopeFirstName = withScopeFirstName,
          scopeSurname = withScopeSurname,
          scopeEmail = withScopeEmail,
          tokenType = withTokenType,
          tokenAccess = withTokenAccess,
          tokenRefresh = withTokenRefresh,
          tokenExpiresIn = withTokenExpiresIn,
          tokenGeneratedIn = withTokenGeneratedIn
      )
  }
}
