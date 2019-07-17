package infrastructure.test.persistence.user.UserRepository

import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import infrastructure.persistence.user.{UserRepository, UserSchema}
import test.domain.model.user.{BuildUser, BuildUserProfile}

class UserRepositoryOnUpdateSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]
  val userRepository = new UserRepository()

  test("Can update some fields of user") {
    userRepository.create(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(
          withFirstname = "francisco",
          withEmail = "email1"
        )
      )
    )

    val user1 = userRepository.findByEmail("email1").get

    user1
      .updateFirstname("manolo")
      .updateSurname("gonzalez")


    userRepository.update(user1)

    val user2 = userRepository.findByEmail("email1").get

    assert(user2.getProfile.firstname === "manolo")
    assert(user2.getProfile.surname === "gonzalez")
  }
  
  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
