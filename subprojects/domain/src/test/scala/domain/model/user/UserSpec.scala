package test.domain.model.user

import org.scalatest.FunSuite

class UserSpec extends FunSuite {

  test("Can compare two owners") {
    val owner1 = BuildUser.any(
      withId = BuildUserId.any(withValue = "email1")
    )

    val owner2 = BuildUser.any(
      withId = BuildUserId.any(withValue = "email2")
    )

    val owner3 = BuildUser.any(
      withId = BuildUserId.any(withValue = "email2")
    )

    assert(owner1.equals(owner2) === false)
    assert(owner2.equals(owner3) === true)
  }

  test("Can edit profile") {
    val owner = BuildUser.any(
      withProfile = BuildUserProfile.specific1()
    )

    assert(owner.getProfile.firstname === "firstname", "the original firstname should be 'firstname'")
    assert(owner.getProfile.email === "oneemail@domain.com", "the original email should be 'oneemail@domain.com'")

    owner
      .updateFirstname("new firstname")
      .updateEmail("new_email@newdomain.com")

    assert(owner.getProfile.firstname === "new firstname", "After updating firstname should be new_firstname")
    assert(owner.getProfile.email === "new_email@newdomain.com", "After updating email should be new_email@newdomain.com")
  }

  test("Can edit dateBirth") {
    val owner = BuildUser.any(
      withProfile = BuildUserProfile.specific1()
    )

    assert(owner.getProfile.datebirth.isInstanceOf[Some[_]])

    owner.updateDateBirth(datebirth = None)

    assert(owner.getProfile.datebirth === None)
  }

  test("Set confirmed email to false after updating email") {
    val owner = BuildUser.any(
      withProfile = BuildUserProfile.any(
        withEmail = "myemail"
      ),
      withEmailConfirmed = true
    )

    owner.updateEmail("any new email")

    assert(owner.isEmailConfirmed() === false)
  }
}
