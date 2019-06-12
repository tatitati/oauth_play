package infrastructure.test.persistence.user

import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery
import test.domain.builders.BuildDate
import infrastructure.persistence.user.{UserPersistentModel, UserSchema}

class UserSchemaSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]

  test("database forconfig type is Database") {
    assert(dbConnection.isInstanceOf[Database])
  }

  test("user table exists") {
    val tables = exec(MTable.getTables).toList
    assert(tables.exists(_.name.name == "user") === true)
  }

  test("Can save user including some dates") {
    val persistentModel = BuildUserPersistentModel.anyNoPersisted(
      withDateBirth = Some(BuildDate.specificMoment())
    )

    assert(persistentModel.datebirth.isInstanceOf[Some[_]])
    assert(persistentModel.datebirth.get.toString() === "2030-08-20T10:02:20.833Z")
    exec(userSchema += persistentModel)
  }

  test("I can receive a persistent model from a db user") {
    // insert
    val persistentModel = BuildUserPersistentModel.anyNoPersisted(
      withDateBirth = Some(BuildDate.specificMoment())
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
