package infrastructure.test.persistence.user

import infrastructure.persistence.user.{UserMapper, UserPersistentModel}
import org.scalatest.FunSuite
import test.domain.model.user.{BuildUser, BuildUserProfile}

class MapperToPersistentSpec extends FunSuite {

  test("Domain is mapped to persistence") {

    val givenDomain = BuildUser.any(
      withProfile = BuildUserProfile.specific()
    )
    val thenPersistence = UserMapper.toPersistent(givenDomain)

    assert(thenPersistence.isInstanceOf[UserPersistentModel], "Should be an instance of OwnerProfile")
    assert(thenPersistence.datebirth.dayOfMonth().get() === 10, "day month birth should match")
    assert(thenPersistence.datebirth.year().get() === 1900, "year birth should match")
  }

  test("Surrogate id is also mapped properly to persistence") {
    val givenDomain = BuildUser.any(withSurrogateId = Some(6))
    val thenPersistent = UserMapper.toPersistent(givenDomain)

    assert(thenPersistent.id === Some(6))
  }
}
