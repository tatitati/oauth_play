package test.domain.model.third

import test.domain.model.BuildSurrogateId
import domain.model.third._

object BuildThird {
    def any(
             withSurrogateId: Option[Long] =  BuildSurrogateId.anyPersisted(),
             withProfile: ThirdProfile = BuildThirdProfile.any(),
             withAccount: ThirdAccount = BuildThirdAccount.any(),
        ): Third = {

      val third = new Third(
        profile = withProfile,
        account = withAccount
      )

      if (withSurrogateId != None) {
        third.setSurrogateId(withSurrogateId)
      }

      third
    }

}
