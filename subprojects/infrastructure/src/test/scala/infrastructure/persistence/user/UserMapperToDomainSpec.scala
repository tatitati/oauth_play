package infrastructure.test.persistence.user

import domain.model.user.User
import infrastructure.persistence.user.UserMapper
import org.joda.time.DateTime
import org.scalatest.FunSuite

class UserMapperToDomainSpec extends FunSuite {

  test("Fields are mapped to domain") {
    val givenPersistent = BuildUserPersistentModel.anyPersisted(
      withFirstname = "any firstname",
      withEmail = "one_email",
      withDateBirth = Some(
        (new DateTime).withYear(2016).withDayOfMonth(1)
      )
    )

    val thenDomain = UserMapper.toDomain(givenPersistent)

    assert(thenDomain.isInstanceOf[User], "Should be an instance of OwnerProfile")
    assert(thenDomain.getSurrogateId().get.isInstanceOf[Long])
    assert(thenDomain.getProfile.firstname === "any firstname", "email should match")
    assert(thenDomain.getProfile.datebirth.get.dayOfMonth().get() === 1, "day month birth should match")
    assert(thenDomain.getProfile.datebirth.get.year().get() === 2016, "year birth should match")
  }


  test("A persisted must have a surrogate id in order to be mapped to domain") {
    val givenPersistent = BuildUserPersistentModel.anyNoPersisted()

    val thrown = intercept[IllegalArgumentException] {
      UserMapper.toDomain(givenPersistent)
    }
    assert(thrown.getMessage() === "A persisted UserPersistentModel is expected to have a surrogate id in order to be mapped to domain")
  }
}
