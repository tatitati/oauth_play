package application.user

import test.domain.builders.Faker

object BuildRegisterRequest {

  def any(
         withUsername: String = Faker.text(),
         withEmail: String = Faker.text(),
         withFirstname: String = Faker.text(),
         withSurname: String = Faker.text(),
         withPassword: String = Faker.text()
         ): RegisterRequest = {
    RegisterRequest(
      username = withUsername,
      email = withEmail,
      firstname = withFirstname,
      surname = withSurname,
      password = withPassword
    )
  }
}
