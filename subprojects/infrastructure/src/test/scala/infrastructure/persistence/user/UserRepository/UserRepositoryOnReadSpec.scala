package infrastructure.test.persistence.user.UserRepository

import domain.model.user.User
import infrastructure.persistence.user.{UserRepository, UserSchema}
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.user.{BuildUser, BuildUserProfile}

class UserRepositoryOnReadSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]
  val userRepository = new UserRepository()

  test("Return Some[User] when finding an existing user") {
    userRepository.create(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(
          withEmail = "anyemail"
        )
      )
    )

    val user = userRepository.findByEmail("anyemail")

    assert(user.isInstanceOf[Some[User]])
    assert(user.get.getProfile.email === "anyemail")
  }

  test("Return None on Read if user is not found") {
    val user = userRepository.findByEmail("anyemail")
    assert(user === None)
  }

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
