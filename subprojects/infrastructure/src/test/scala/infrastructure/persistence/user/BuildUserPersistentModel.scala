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
           withDateBirth: DateTime = BuildDate.any(),
           withRegisteredDateTime: DateTime = BuildDate.any(),
           withIsEmailConfirmed: Boolean = Faker.boolean(),
           withEmail: String = Faker.text(),
           withHashPassword: String = Faker.text()
         ): UserPersistentModel = {
    UserPersistentModel(
      withSurrogateId,
      withFirstname,
      withSurname,
      withDateBirth,
      withRegisteredDateTime,
      withIsEmailConfirmed,
      withEmail,
      withHashPassword
    )
  }

  def anyPersisted(
                    withSurrogateId: Option[Long] =  BuildSurrogateId.anyPersisted(),
                    withFirstname: String = Faker.text(),
                    withSurname: String = Faker.text(),
                    withDateBirth: DateTime = BuildDate.any(),
                    withRegisteredDateTime: DateTime = BuildDate.any(),
                    withIsEmailConfirmed: Boolean = Faker.boolean(),
                    withEmail: String = Faker.text(),
                    withHashPassword: String = Faker.text()
                  ): UserPersistentModel = {
    UserPersistentModel(
      withSurrogateId,
      withFirstname,
      withSurname,
      withDateBirth,
      withRegisteredDateTime,
      withIsEmailConfirmed,
      withEmail,
      withHashPassword
    )
  }

  def anyNoPersisted(
                      withFirstname: String = Faker.text(),
                      withSurname: String = Faker.text(),
                      withDateBirth: DateTime = BuildDate.any(),
                      withRegisteredDateTime: DateTime = BuildDate.any(),
                      withIsEmailConfirmed: Boolean = Faker.boolean(),
                      withEmail: String = Faker.text(),
                      withHashPassword: String = Faker.text()
                    ): UserPersistentModel = {
    UserPersistentModel(
      None,
      withFirstname,
      withSurname,
      withDateBirth,
      withRegisteredDateTime,
      withIsEmailConfirmed,
      withEmail,
      withHashPassword
    )
  }
}
