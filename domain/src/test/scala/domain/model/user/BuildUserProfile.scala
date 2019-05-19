package test.domain.model.user

import domain.model.user.UserProfile
import com.github.nscala_time.time.Imports.DateTime
import test.domain.builders.{BuildDate, Faker}

object BuildUserProfile {

  def any(
           withFirstname: String =  Faker.text(),
           withSurname: String = Faker.text(),
           withDatebirth: DateTime = BuildDate.any()
         ): UserProfile = {

    new UserProfile(
      firstname = withFirstname,
      surname = withSurname,
      datebirth = withDatebirth
    )
  }

  def specific(): UserProfile = {
    new UserProfile(
      firstname = "firstname",
      surname = "surname",
      datebirth = new DateTime("1900-03-10")
    )
  }
}
