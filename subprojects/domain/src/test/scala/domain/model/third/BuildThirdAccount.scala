package domain.model.third

import com.github.nscala_time.time.Imports.DateTime
import domain.model.user.UserAccount
import test.domain.builders.{BuildDate, Faker}

object BuildThirdAccount {
  def any(
           withUsername: String =  Faker.text(),
           withSalt: String = Faker.text(),
           withHashedPassword: String = Faker.text(),
           withEmailConfirmed: Boolean = Faker.boolean(),
           withRegisteredDateTime: DateTime = BuildDate.any()
         ): ThirdAccount = {

    ThirdAccount(
      username = withUsername,
      salt = withSalt,
      hashedPassword = withHashedPassword,
      emailConfirmed = withEmailConfirmed,
      registeredDateTime = withRegisteredDateTime
    )
  }

  def anySpecific(): UserAccount = {
    UserAccount(
      username = "one_user_name",
      salt = "aabbcc12344",
      hashedPassword = "123456789=-=-=-==-=",
      emailConfirmed = true,
      registeredDateTime = BuildDate.specificMoment()
    )
  }
}
