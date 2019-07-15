package infrastructure.test.persistence.third

import infrastructure.persistence.third.ThirdPersistentModel
import test.domain.builders.{BuildDate, BuildUuid, Faker}
import com.github.nscala_time.time.Imports.DateTime

object BuildThirdPersistentModel {

  def any(
           withSurrogateId: Option[Long] = Faker(Some(Faker.long()), None),
           withEmail: String = Faker.text(),
           withUsername: String = Faker.text(),
           withName: String = Faker.text(),
           withDescription: String = Faker.text(),
           withSalt: String = Faker.text(),
           withHashPassword: String = Faker.text(),
           withRegisteredDateTime: DateTime = BuildDate.any(),
           withEmailConfirmed: Boolean = Faker.boolean()
         ): ThirdPersistentModel = {
    ThirdPersistentModel(
      withSurrogateId,
      withEmail,
      withUsername,
      withName,
      withDescription,
      withSalt,
      withHashPassword,
      withRegisteredDateTime,
      withEmailConfirmed
    )
  }

  def anyPersisted(
            withEmail: String = Faker.text(),
            withUsername: String = Faker.text(),
            withName: String = Faker.text(),
            withDescription: String = Faker.text(),
            withSalt: String = Faker.text(),
            withHashPassword: String = Faker.text(),
            withRegisteredDateTime: DateTime = BuildDate.any(),
            withEmailConfirmed: Boolean = Faker.boolean()
         ): ThirdPersistentModel = {
    any(
      Some(Faker.long()),
      withEmail,
      withUsername,
      withName,
      withDescription,
      withSalt,
      withHashPassword,
      withRegisteredDateTime,
      withEmailConfirmed
    )
  }

  def anyNoPersisted(
            withEmail: String = Faker.text(),
            withUsername: String = Faker.text(),
            withName: String = Faker.text(),
            withDescription: String = Faker.text(),
            withSalt: String = Faker.text(),
            withHashPassword: String = Faker.text(),
            withRegisteredDateTime: DateTime = BuildDate.any(),
            withEmailConfirmed: Boolean = Faker.boolean()
          ): ThirdPersistentModel = {
    any(
      None,
      withEmail,
      withUsername,
      withName,
      withDescription,
      withSalt,
      withHashPassword,
      withRegisteredDateTime,
      withEmailConfirmed
    )
  }
}
