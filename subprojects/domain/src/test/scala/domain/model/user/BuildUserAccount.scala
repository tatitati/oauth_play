package test.domain.model.user

import domain.model.user.{UserAccount}
import com.github.nscala_time.time.Imports.DateTime
import test.domain.builders.{BuildDate, Faker}

object BuildUserAccount {
  def any(
           withUsername: String =  Faker.text(),
           withSalt: String = Faker.text(),
           withHashedPassword: String = Faker.text(),
           withEmailConfirmed: Boolean = Faker.boolean(),
           withRegisteredDateTime: DateTime = BuildDate.any()
         ): UserAccount = {

    UserAccount(
      withUsername,
      withSalt,
      withHashedPassword,
      withEmailConfirmed,
      withRegisteredDateTime
    )
  }

  def anySpecific(): UserAccount = {
    UserAccount(
      "one_user_name",
      salt = "aabbcc12344",
      hashedPassword = "123456789=-=-=-==-=",
      emailConfirmed = true,
      registeredDateTime = BuildDate.specificMoment()
    )
  }
}

