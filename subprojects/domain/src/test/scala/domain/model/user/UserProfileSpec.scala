package test.domain.model.user

import org.scalatest.FunSuite

class UserProfileSpec extends FunSuite {

  test("Has proper attributes") {
    val givenProfile = BuildUserProfile.any(withFirstname = "anyfirstname")

    assert(givenProfile.firstname === "anyfirstname")
  }
}
