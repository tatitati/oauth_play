package test.domain.model.thirdapp

import domain.model.thirdapp.{Thirdapp, ThirdappCredentials, ThirdappId, ThirdappProfile}
import domain.model.third.ThirdId
import test.domain.model.third.BuildThirdId

object BuildThirdapp {

  def any(
           withSiteId: ThirdappId = BuildThirdappId.any(),
           withThirdId: ThirdId = BuildThirdId.any(),
           withCredentials: ThirdappCredentials = BuildThirdappCredentials.any(),
           withProfile: ThirdappProfile = BuildThirdappProfile.any()
         ): Thirdapp = {

    new Thirdapp(
      thirdappId = withSiteId,
      thirdId = withThirdId,
      credentials = withCredentials,
      profile = withProfile
    )
  }
}
