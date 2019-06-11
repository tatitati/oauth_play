package infrastructure.test.persistence.user

import com.github.nscala_time.time.Imports.DateTime
import infrastructure.persistence.user.UserPersistentModel
import test.domain.builders.{BuildDate, Faker}
import test.domain.model.BuildSurrogateId

object BuildUserPersistentModel {

  def any(
           withSurrogateId: Option[Long] =  BuildSurrogateId.any(),
           withFirstname: String = Faker.text(),
           withSurname: String = Faker.text(),
           withEmail: String = Faker.text(),
           withDateBirth: Option[DateTime] = Faker(Some(BuildDate.any()), None),
           withUsername: String = Faker.text(),
           withSalt: String = Faker.text(),
           withHashPassword: String = Faker.text(),
           withRegisteredDateTime: DateTime = BuildDate.any(),
           withEmailConfirmed: Boolean = Faker.boolean(),
         ): UserPersistentModel = {
    UserPersistentModel(
      withSurrogateId,
      withFirstname,
      withSurname,
      withEmail,
      withDateBirth,
      withUsername,
      withSalt,
      withHashPassword,
      withRegisteredDateTime,
      withEmailConfirmed
    )
  }

  def anyPersisted(
          withSurrogateId: Option[Long] =  BuildSurrogateId.anyPersisted(),
          withFirstname: String = Faker.text(),
          withSurname: String = Faker.text(),
          withEmail: String = Faker.text(),
          withDateBirth: Option[DateTime] = Faker(Some(BuildDate.any()), None),
          withUsername: String = Faker.text(),
          withSalt: String = Faker.text(),
          withHashPassword: String = Faker.text(),
          withRegisteredDateTime: DateTime = BuildDate.any(),
          withEmailConfirmed: Boolean = Faker.boolean()
    ): UserPersistentModel = {
          UserPersistentModel(
            withSurrogateId,
            withFirstname,
            withSurname,
            withEmail,
            withDateBirth,
            withUsername,
            withSalt,
            withHashPassword,
            withRegisteredDateTime,
            withEmailConfirmed
          )
  }

  def anyNoPersisted(
                      withFirstname: String = Faker.text(),
                      withSurname: String = Faker.text(),
                      withEmail: String = Faker.text(),
                      withDateBirth: Option[DateTime] = Faker(Some(BuildDate.any()), None),
                      withUsername: String = Faker.text(),
                      withSalt: String = Faker.text(),
                      withHashPassword: String = Faker.text(),
                      withRegisteredDateTime: DateTime = BuildDate.any(),
                      withEmailConfirmed: Boolean = Faker.boolean()
                    ): UserPersistentModel = {
    UserPersistentModel(
      None,
      withFirstname,
      withSurname,
      withEmail,
      withDateBirth,
      withUsername,
      withSalt,
      withHashPassword,
      withRegisteredDateTime,
      withEmailConfirmed
    )
  }
}
