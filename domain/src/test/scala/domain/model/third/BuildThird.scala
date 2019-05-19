package test.domain.model.third

import test.domain.model.BuildSurrogateId
import domain.model.third.ThirdId
import domain.model.third.{Third, ThirdCredentials, ThirdProfile}
import test.domain.builders.BuildUuid

object BuildThird {
    def any(
             withSurrogateId: Option[Long] =  BuildSurrogateId.anyPersisted(),
             withId: ThirdId = ThirdId(BuildUuid.any()),
             withThirdProfile: ThirdProfile = BuildThirdProfile.any(),
        ): Third = {

      val third = new Third(withId, withThirdProfile)

      if (withSurrogateId != None) {
        third.setSurrogateId(withSurrogateId)
      }

      third
    }

}
