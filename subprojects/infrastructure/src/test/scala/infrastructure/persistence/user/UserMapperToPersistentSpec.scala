package infrastructure.test.persistence.user

import infrastructure.persistence.user.{UserMapper, UserPersistentModel}
import org.scalatest.FunSuite
import test.domain.model.user.{BuildUser, BuildUserProfile}

class UserMapperToPersistentSpec extends FunSuite {

  test("Domain is mapped to persistence") {
    val givenDomain = BuildUser.anyPersisted(
      withProfile = BuildUserProfile.anySpecific()
    )
    val thenPersistence = UserMapper.toPersistent(givenDomain)

    assert(thenPersistence.isInstanceOf[UserPersistentModel], "Should be an instance of OwnerProfile")
    assert(thenPersistence.datebirth.get.dayOfMonth().get() === 10, "day month birth should match")
    assert(thenPersistence.datebirth.get.year().get() === 1900, "year birth should match")
    assert(thenPersistence.datebirth.get.toString() === "1900-03-10T00:00:00.000Z", "date format should match")
  }

  test("Surrogate id is also mapped properly to persistence") {
    val givenDomain = BuildUser.anyPersisted(withSurrogateId = Some(6))
    val thenPersistent = UserMapper.toPersistent(givenDomain)

    assert(thenPersistent.id === Some(6))
  }
}
