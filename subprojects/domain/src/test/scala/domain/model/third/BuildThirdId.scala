package test.domain.model.third

import domain.model.third.ThirdId
import test.domain.builders.Faker

object BuildThirdId {

  def any(withValue: String = Faker.text()): ThirdId = {
    ThirdId(withValue)
  }

  def specific(): ThirdId = {
    ThirdId("any_third_id_")
  }
}