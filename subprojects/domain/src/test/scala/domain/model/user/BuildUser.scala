package test.domain.model.user

import test.domain.model.BuildSurrogateId
import domain.model.user.User
import domain.model.user._
import com.github.nscala_time.time.Imports.DateTime
import test.domain.builders.{BuildDate, Faker}

object BuildUser {

  // no persisted = just registered
  def anyNoPersisted(
                      withHashCredentials: String =  Faker.text(),
                      withId: UserId = BuildUserId.any(),
                      withProfile: UserProfile = BuildUserProfile.any(),
                      withRegisteredDateTime: DateTime = BuildDate.any(),
                    ):User  = {
    val user = new User(
      withId,
      withProfile,
      withRegisteredDateTime,
      emailConfirmed = false
    )

    user.setHashcredentials(withHashCredentials)
    user
  }

    def anyPersisted(
             withSurrogateId: Option[Long] =  BuildSurrogateId.anyPersisted(),
             withHashCredentials: String =  Faker.text(),
             withId: UserId = BuildUserId.any(),
             withProfile: UserProfile = BuildUserProfile.any(),
             withRegisteredDateTime: DateTime = BuildDate.any(),
             withEmailConfirmed: Boolean = Faker.boolean()
           ):User  = {
      val user = new User(
        withId,
        withProfile,
        withRegisteredDateTime,
        withEmailConfirmed
      )

      user.setSurrogateId(withSurrogateId)
      user.setHashcredentials(withHashCredentials)
      user
    }
}
