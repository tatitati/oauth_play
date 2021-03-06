package infrastructure.test.persistence.user.UserRepository

import infrastructure.persistence.user.{UserPersistentModel, UserRepository, UserSchema}
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.user.BuildUser

class UserRepositoryOnCreateSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]
  val userRepository = new UserRepository()

  test("Can insert a new user") {
    userRepository.create(
      BuildUser.anyNoPersisted()
    )

    val rows = exec(userSchema.result)

    assert(rows.size === 1)
    assert(rows.isInstanceOf[Vector[_]])
    assert(rows.head.isInstanceOf[UserPersistentModel])
  }

  test("Surrogate id is generated BY THE DB once is saved") {
    userRepository.create(
      BuildUser.anyNoPersisted()
    )

    val persistentModel = exec(userSchema.result).head

    assert(persistentModel.id.isInstanceOf[Some[_]])
    assert(persistentModel.id.get.isInstanceOf[Long])
    assert(persistentModel.id.get === 1)
  }

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
