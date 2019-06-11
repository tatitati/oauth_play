package test.domain.model.user

import test.domain.model.BuildSurrogateId
import domain.model.user.User
import domain.model.user._
import test.domain.builders.Faker

object BuildUser {
  def anyNoPersisted(
      withProfile: UserProfile = BuildUserProfile.any(),
      withUserAccount: UserAccount = BuildUserAccount.any(),
    ):User  = {

      new User(
        withProfile,
        withUserAccount
      )
  }

    def anyPersisted(
             withSurrogateId: Option[Long] =  BuildSurrogateId.anyPersisted(),
             withProfile: UserProfile = BuildUserProfile.any(),
             withUserAccount: UserAccount = BuildUserAccount.any(),
           ):User  = {

      var user = new User(
        withProfile,
        withUserAccount
      )

      user.setSurrogateId(withSurrogateId)
      user
    }

  def any(
        withSurrogateId: Option[Long] =  Faker(BuildSurrogateId.anyPersisted(), None),
        withProfile: UserProfile = BuildUserProfile.any(),
        withUserAccount: UserAccount = BuildUserAccount.any(),
      ):User  = {

    var user = new User(
      withProfile,
      withUserAccount
    )

    if (withSurrogateId != None) {
      user.setSurrogateId(withSurrogateId)
    }

    user
  }
}
