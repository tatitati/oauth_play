package test.domain.model.user

import org.scalatest.FunSuite

class UserSpec extends FunSuite {

  test("Can compare two owners") {
    val user1 = BuildUser.anyPersisted(
      withId = BuildUserId.any(withValue = "email1")
    )

    val user2 = BuildUser.anyPersisted(
      withId = BuildUserId.any(withValue = "email2")
    )

    val user3 = BuildUser.anyPersisted(
      withId = BuildUserId.any(withValue = "email2")
    )

    assert(user1.equals(user2) === false)
    assert(user2.equals(user3) === true)
  }

  test("Can edit profile") {
    val owner = BuildUser.anyPersisted(
      withProfile = BuildUserProfile.anySpecific()
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
    val owner = BuildUser.anyPersisted(
      withProfile = BuildUserProfile.anySpecific()
    )

    assert(owner.getProfile.datebirth.isInstanceOf[Some[_]])

    owner.updateDateBirth(datebirth = None)

    assert(owner.getProfile.datebirth === None)
  }

  test("Set confirmed email to false after updating email") {
    val owner = BuildUser.anyPersisted(
      withProfile = BuildUserProfile.any(
        withEmail = "myemail"
      ),
      withEmailConfirmed = true
    )

    owner.updateEmail("any new email")

    assert(owner.isEmailConfirmed() === false)
  }

  test("I can set a hash credential in the user") {
    val user = BuildUser.anyNoPersisted(
      withHashCredentials = "myhashcredentials"
    )

    assert(user.getHashcredentials() === "myhashcredentials")
  }
}
