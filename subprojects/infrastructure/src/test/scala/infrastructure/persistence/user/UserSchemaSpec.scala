package infrastructure.test.persistence.user

import infrastructure.persistence.third.ThirdPersistentModel
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery
import test.domain.builders.BuildDate
import infrastructure.persistence.user.{UserPersistentModel, UserSchema}

class UserSchemaSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]

  test("database forconfig type is:") {
    assert(dbConnection.isInstanceOf[Database])
  }

  test("owner_profile table exists") {
    val tables = exec(MTable.getTables).toList
    assert(tables.exists(_.name.name == "user") === true)
  }

  test("Can save one owner profile persistence model including some dates") {
    val persistentModel = BuildUserPersistentModel.anyNoPersisted(
      withDateBirth = BuildDate.specificMoment()
    )

    assert(persistentModel.datebirth.toString() === "2030-08-20T10:02:20.833Z")
    exec(userSchema += persistentModel)
  }

  test("I can receive a persistent model from a db user") {
    // insert
    val persistentModel = BuildUserPersistentModel.anyNoPersisted(
      withDateBirth = BuildDate.specificMoment()
    )
    exec(userSchema += persistentModel)

    // read
    val rows = exec(userSchema.result)
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
