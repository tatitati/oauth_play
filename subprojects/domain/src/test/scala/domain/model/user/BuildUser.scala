package test.domain.model.user

import test.domain.model.BuildSurrogateId
import domain.model.user.User
import domain.model.user._
import com.github.nscala_time.time.Imports.DateTime
import test.domain.builders.{BuildDate, Faker}

object BuildUser {
    def any(
             withSurrogateId: Option[Long] =  BuildSurrogateId.any(),
             withId: UserId = BuildUserId.any(),
             withProfile: UserProfile = BuildUserProfile.any(),
             withRegisteredDateTime: DateTime = BuildDate.any(),
             withEmailConfirmed: Boolean = Faker.boolean(),
             withUserCredentials: UserCredentials = BuildUserCredentials.any()
           ):User  = {
      val user = new User(
        withId,
        withProfile,
        withRegisteredDateTime,
        withEmailConfirmed,
        withUserCredentials
      )

      if(withSurrogateId != None) {
        user.setSurrogateId(withSurrogateId)
      }

      user
    }
}
