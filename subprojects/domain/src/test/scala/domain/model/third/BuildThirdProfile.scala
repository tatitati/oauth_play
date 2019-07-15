package test.domain.model.third

import domain.model.third.ThirdProfile
import test.domain.builders.Faker

object BuildThirdProfile {

    def any(
             withEmail: String = Faker.text(),
             withName: String = Faker.text(),
             withDescription: String = Faker.text()
     ): ThirdProfile = {
        ThirdProfile(
          email = withEmail,
          name = withName,
          description = withDescription
      )
    }

  def specific(): ThirdProfile = {
    ThirdProfile(
      email = "third_email@whatever.com",
      name = "third--name",
      description = "company dedicated to do some shit"
    )
  }

}
