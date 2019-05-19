package infrastructure.test.persistence.user

import infrastructure.persistence.Exec
import infrastructure.persistence.user.UserSchema
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery

class UserSchemaSpec extends FunSuite with BeforeAndAfterEach with Exec {
  val ownerSchema = TableQuery[UserSchema]
  implicit val db = Database.forConfig("mydb")

  test("database forconfig type is:") {
    assert(db.isInstanceOf[Database])
  }

  test("owner_profile table exists") {
    val tables = exec(MTable.getTables).toList
    assert(tables.exists(_.name.name == "user") === true)
  }

  test("Can save one owner profile persistence model") {
    val persistentModel = BuildUserPersistentModel.anyNoPersisted()
    exec(ownerSchema += persistentModel)
  }

  override def beforeEach() {
    exec(ownerSchema.schema.dropIfExists)
    exec(ownerSchema.schema.create)
    //sadfasdf
  }
}
