package application.test.user

import application.user.{BuildRegisterRequest, RegisterCommand}
import infrastructure.persistence.user.{UserPersistentModel, UserRepository, UserSchema}
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

class RegisterCommandSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {

  val userSchema = TableQuery[UserSchema]
  val registerCommand = new RegisterCommand(
    userRepository = new UserRepository()
  )

  test("Can register a non existing user") {
    val givenRegisterRequest = BuildRegisterRequest.any(
      withSurname = "my_username"
    )

    registerCommand.exec(givenRegisterRequest)

    val rows = exec(userSchema.result)

    assert(rows.size === 1)
    assert(rows.isInstanceOf[Vector[_]])
    assert(rows.head.isInstanceOf[UserPersistentModel])
  }

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
