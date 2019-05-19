package infrastructure.test.persistence.third

import infrastructure.persistence.third.ThirdPersistentModel
import test.domain.builders.{BuildUuid, Faker}

object BuildThirdPersistentModel {

  def any(
           withSurrogateId: Option[Long] = Faker(Some(Faker.long()), None),
           withId: String = BuildUuid.any().toString,
           withName: String = Faker.text(),
           withDescription: String = Faker.text()
         ): ThirdPersistentModel = {
    ThirdPersistentModel(
        withSurrogateId,
        withId,
        withName,
        withDescription
    )
  }

  def anyPersisted(
            withId: String = BuildUuid.any().toString,
            withName: String = Faker.text(),
            withDescription: String = Faker.text()
         ): ThirdPersistentModel = {
    any(
      withSurrogateId = Faker(Some(Faker.int())),
      withId,
      withName,
      withDescription
    )
  }

  def anyNoPersisted(
            withId: String = BuildUuid.any().toString,
            withName: String = Faker.text(),
            withDescription: String = Faker.text()
          ): ThirdPersistentModel = {
    any(
      withSurrogateId = None,
      withId,
      withName,
      withDescription
    )
  }
}
