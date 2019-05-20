package test.domain.model.auth

import domain.model.Scope
import domain.model.thirdapp.ThirdappId
import domain.model.auth._
import domain.model.user.UserId
import test.domain.model.BuildScope
import test.domain.model.thirdapp.BuildThirdappId
import test.domain.model.user.BuildUserId

object BuildAuth {

  def any(
           withSurrogateId: Option[Long] = None,
           withId: AuthId = BuildAuthId.any(),
           withThirdappId: ThirdappId = BuildThirdappId.any(),
           withUserId: UserId = BuildUserId.any(),
           withScope: Scope = BuildScope.any(),
           withToken: AuthToken = BuildToken.any()
         ): Auth = {
    val auth = new Auth(
      authId = withId,
      thirdappId = withThirdappId,
      userId = withUserId,
      scope = withScope,
      token = withToken
    )

    if(withSurrogateId != None) {
      auth.setSurrogateId(withSurrogateId)
    }

    auth
  }

  def specific(): Auth = {
    new Auth(
      authId = BuildAuthId.specific1(),
      thirdappId = BuildThirdappId.specific2(),
      userId = BuildUserId.specific1(),
      scope = BuildScope.onlyEmailAndFirstname(),
      token = BuildToken.specific()
    )
  }
}
