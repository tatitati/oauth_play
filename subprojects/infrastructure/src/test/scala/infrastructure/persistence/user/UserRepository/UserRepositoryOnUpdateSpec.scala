package infrastructure.test.persistence.user.UserRepository

import infrastructure.persistence.user.{UserRepository, UserSchema}
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.user.{BuildUser, BuildUserCredentials, BuildUserProfile}

class UserRepositoryOnUpdateSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]

  test("Can update profile of existing user") {
    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(withFirstname = "francisco"),
        withUserCredentials = BuildUserCredentials.any(withEmail = "anyemail")
      )
    )

    val user = UserRepository.findByEmail("anyemail").head
    assert(user.getProfile.firstname === "francisco", "original firstname is francisco")

    user.updateFirstname("paco")
    UserRepository.save(user)

    val thenUserUpdated = UserRepository.findByEmail("anyemail").head
    assert(thenUserUpdated.getProfile.firstname === "paco", "after update it, the firstname should be paco")
  }

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
