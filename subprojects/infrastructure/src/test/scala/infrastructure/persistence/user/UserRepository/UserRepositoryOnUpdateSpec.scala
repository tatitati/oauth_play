package infrastructure.test.persistence.user.UserRepository

import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import infrastructure.persistence.user.{UserRepository, UserSchema}
import test.domain.model.user.{BuildUser, BuildUserCredentials, BuildUserProfile}

class UserRepositoryOnUpdateSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]

  test("Can update some fields of user") {
    val user1 = UserRepository.findByEmail("email1").get

    user1.updateFirstname("manolo")
    user1.updateSurname("gonzalez")

    UserRepository.update(user1)

    val user2 = UserRepository.findByEmail("email1").get

    assert(user2.getProfile.firstname === "manolo")
    assert(user2.getProfile.surname === "gonzalez")
  }


  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)

    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(withFirstname = "francisco"),
        withUserCredentials = BuildUserCredentials.any(withEmail = "email1")
      )
    )
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
