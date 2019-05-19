package test.domain.model.user

import domain.model.user.UserCredentials
import test.domain.builders.Faker

object BuildUserCredentials {
  def any(
         withEmail: String = Faker.text(),
         withHashPassword: String = Faker.text()
         ): UserCredentials = {
      UserCredentials(withEmail, withHashPassword)
  }
}
