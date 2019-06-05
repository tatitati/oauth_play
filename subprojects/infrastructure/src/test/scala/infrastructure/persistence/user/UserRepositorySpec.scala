package infrastructure.test.persistence.user

import domain.model.user.User
import infrastructure.persistence.user.{UserPersistentModel, UserRepository, UserSchema}
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.user.{BuildUser, BuildUserCredentials}

class UserRepositorySpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]

  test("Can insert a new user") {
    UserRepository.save(
      BuildUser.any(
        withSurrogateId = None
      )
    )

    val rows = exec(userSchema.result)

    assert(rows.size === 1)
    assert(rows.isInstanceOf[Vector[_]])
    assert(rows.head.isInstanceOf[UserPersistentModel])
  }

  test("Return Some[User] when finding an existing user") {
    UserRepository.save(
      BuildUser.any(
        withSurrogateId = None,
        withUserCredentials = BuildUserCredentials.any(
          withEmail = "anyemail"
        )
      )
    )

    val user = UserRepository.findByEmail("anyemail")

    assert(user.isInstanceOf[Some[User]])
    assert(user.get.getCredentials().email === "anyemail")
  }

  test("Return None on Read if user is not found") {
    val user = UserRepository.findByEmail("anyemail")
    assert(user === None)
  }

  test("Return false on checking a non-existing user") {
    val user = UserRepository.existByEmail("anyemail")
    assert(user === false)
  }

  test("Return true on checking an existing user") {
    UserRepository.save(
      BuildUser.any(
        withSurrogateId = None,
        withUserCredentials = BuildUserCredentials.any(
          withEmail = "anyemail"
        )
      )
    )

    val user = UserRepository.existByEmail("anyemail")
    assert(user === true)
  }

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
