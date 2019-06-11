package test.domain.model.user

import domain.model.user.{UserAccount}
import com.github.nscala_time.time.Imports.DateTime
import test.domain.builders.{BuildDate, Faker}

object BuildUserAccount {
  def any(
           withEmail: String =  Faker.text(),
           withSalt: String = Faker.text(),
           withHashedPassword: String = Faker.text(),
           withEmailConfirmed: Boolean = Faker.boolean(),
           withRegisteredDateTime: DateTime = BuildDate.any()
         ): UserAccount = {

    UserAccount(
      withEmail,
      withSalt,
      withHashedPassword,
      withEmailConfirmed,
      withRegisteredDateTime
    )
  }

  def anySpecific(): UserAccount = {
    UserAccount(
      "anyemail@whatever.com",
      salt = "aabbcc12344",
      hashedPassword = "123456789=-=-=-==-=",
      emailConfirmed = true,
      registeredDateTime = BuildDate.specificMoment()
    )
  }
}

