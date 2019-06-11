package test.domain.services

import java.security.MessageDigest
import domain.model.user.User
import domain.services.UserFactory
import org.scalatest.FunSuite

class UserFactorySpec extends FunSuite{
  test("Can create a new user with factory") {
    val user = UserFactory.create(
      withUsername = "my_user_name",
      withFirstname = "francisco",
      withSurname = "gonzaled",
      withEmail = "oneemail@whatever.net",
      withPassword = "1234"
    )

    assert(user.isInstanceOf[User])
  }
}
