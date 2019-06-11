package test.domain.model.user

import org.scalatest.FunSuite

class UserSpec extends FunSuite {

  test("Can compare users") {
    val user1 = BuildUser.any(
      withUserAccount = BuildUserAccount.any(
        withUsername = "user_name_1"
      )
    )

    val user2 = BuildUser.any(
      withUserAccount = BuildUserAccount.any(
        withUsername = "user_name_2"
      )
    )

    val user3 = BuildUser.any(
      withUserAccount = BuildUserAccount.any(
        withUsername = "user_name_2"
      )
    )

    assert(user1.equals(user2) === false)
    assert(user2.equals(user3) === true)
  }

  test("Can edit profile") {
    val owner = BuildUser.anyPersisted(
      withProfile = BuildUserProfile.anySpecific()
    )

    assert(owner.getProfile.firstname === "firstname", "the original firstname should be 'firstname'")
    owner.updateFirstname("new firstname")
    assert(owner.getProfile.firstname === "new firstname", "After updating firstname should be new_firstname")
  }

  test("Can edit dateBirth") {
    val owner = BuildUser.anyPersisted(
      withProfile = BuildUserProfile.anySpecific()
    )

    assert(owner.getProfile.datebirth.isInstanceOf[Some[_]])

    owner.updateDateBirth(datebirth = None)

    assert(owner.getProfile.datebirth === None)
  }

  test("Set confirmed email to false after updating email") {
    val user = BuildUser.anyPersisted(
      withUserAccount = BuildUserAccount.any(
        withEmailConfirmed = true
      ),
      withProfile = BuildUserProfile.any(
        withEmail = "anyemail@whatever.com"
      )
    )

    user.updateEmail("any new email")

    assert(user.isEmailConfirmed() === false)
  }
}
