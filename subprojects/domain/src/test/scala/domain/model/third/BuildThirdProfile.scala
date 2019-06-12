package test.domain.model.third

import domain.model.third.ThirdProfile
import test.domain.builders.Faker

object BuildThirdProfile {

    def any(
             withEmail: String = Faker.text()
     ): ThirdProfile = {
        ThirdProfile(
          email = withEmail
      )
    }

  def specific(): ThirdProfile = {
    ThirdProfile(
      email = "third_email@whatever.com"
    )
  }

}
