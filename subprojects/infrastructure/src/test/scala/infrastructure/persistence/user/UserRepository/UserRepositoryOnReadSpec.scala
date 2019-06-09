package infrastructure.test.persistence.user.UserRepository

import domain.model.user.User
import infrastructure.persistence.user.{UserRepository, UserSchema}
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.user.{BuildUser, BuildUserCredentials}

class UserRepositoryOnReadSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]

  test("Return Some[User] when finding an existing user") {
    UserRepository.save(
      BuildUser.anyNoPersisted(
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

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
