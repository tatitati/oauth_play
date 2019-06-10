package infrastructure.test.persistence.user.UserRepository

import infrastructure.persistence.user.{UserRepository, UserSchema}
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.user.{BuildUser, BuildUserCredentials, BuildUserProfile}

class UserRepositoryOnExistSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]

  test("Return false on checking by email a non-existing user") {
    val user = UserRepository.existByEmail("anyemail")
    assert(user === false)
  }

  test("Return true on checking an existing user") {
    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(
          withEmail = "anyemail"
        )
      )
    )

    val userExists = UserRepository.existByEmail("anyemail")
    assert(userExists === true)
  }

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
