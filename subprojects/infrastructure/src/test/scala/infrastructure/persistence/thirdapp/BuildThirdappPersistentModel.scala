package infrastructure.test.persistence.thirdapp

import infrastructure.persistence.thirdapp.ThirdappPersistentModel
import test.domain.builders.{BuildUuid, Faker}
import test.domain.model.third.BuildThirdId

object BuildThirdappPersistentModel {

  def any(
           withSurrogateId: Option[Long] = Faker(Some(Faker.long()), None),
           withId: String = BuildUuid.any().toString,
           withThirdId: String = BuildThirdId.any().toString,
           withClientId: String = Faker.text(),
           withClientSecret: String = Faker.text(),
           withName: String = Faker.text(),
           withDomain: String = Faker.text(),
           withDescription: String = Faker.text(),
           withHomepage: String = Faker.text(),
           withUrlCallback: String = Faker.text(),
         ): ThirdappPersistentModel = {
    ThirdappPersistentModel(
      withSurrogateId,
      withId,
      withThirdId,
      withClientId,
      withClientSecret,
      withName,
      withDomain,
      withDescription,
      withHomepage,
      withUrlCallback
    )
  }

  def anyPersisted(
                    withId: String = BuildUuid.any().toString,
                    withThirdId: String = BuildThirdId.any().toString,
                    withClientId: String = Faker.text(),
                    withClientSecret: String = Faker.text(),
                    withName: String = Faker.text(),
                    withDomain: String = Faker.text(),
                    withDescription: String = Faker.text(),
                    withHomepage: String = Faker.text(),
                    withUrlCallback: String = Faker.text(),
                  ): ThirdappPersistentModel = {
    ThirdappPersistentModel(
      Some(Faker.long()),
      withId,
      withThirdId,
      withClientId,
      withClientSecret,
      withName,
      withDomain,
      withDescription,
      withHomepage,
      withUrlCallback
    )
  }
  def anyNoPersisted(
                    withId: String = BuildUuid.any().toString,
                    withThirdId: String = BuildThirdId.any().toString,
                    withClientId: String = Faker.text(),
                    withClientSecret: String = Faker.text(),
                    withName: String = Faker.text(),
                    withDomain: String = Faker.text(),
                    withDescription: String = Faker.text(),
                    withHomepage: String = Faker.text(),
                    withUrlCallback: String = Faker.text(),
                  ): ThirdappPersistentModel = {
    ThirdappPersistentModel(
      None,
      withId,
      withThirdId,
      withClientId,
      withClientSecret,
      withName,
      withDomain,
      withDescription,
      withHomepage,
      withUrlCallback
    )
  }
}

