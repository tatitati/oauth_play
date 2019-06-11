package test.domain.model.user

import domain.model.user.UserProfile
import com.github.nscala_time.time.Imports.DateTime
import test.domain.builders.{BuildDate, Faker}

object BuildUserProfile {

  def any(
           withFirstname: String =  Faker.text(),
           withSurname: String = Faker.text(),
           withDatebirth: Option[DateTime] = Faker(Some(BuildDate.any()), None),
           withEmail: String = Faker.text()
         ): UserProfile = {

    UserProfile(
      firstname = withFirstname,
      surname = withSurname,
      datebirth = withDatebirth,
      email = withEmail
    )
  }

  def anySpecific(): UserProfile = {
    UserProfile(
      firstname = "firstname",
      surname = "surname",
      datebirth = Some(new DateTime("1900-03-10")),
      email = "email@whatever.com"
    )
  }
}
