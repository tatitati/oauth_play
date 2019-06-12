package test.domain.services

import domain.model.third.Third
import domain.services.ThirdFactory
import org.scalatest.FunSuite

class ThirdFactorySpec extends FunSuite{
  test("Can create a new user with factory") {
    val third = ThirdFactory.create(
      withUsername = "my_user_name",
      withFirstname = "francisco",
      withSurname = "gonzaled",
      withEmail = "oneemail@whatever.net",
      withPassword = "1234"
    )

    assert(third.isInstanceOf[Third])
  }
}
