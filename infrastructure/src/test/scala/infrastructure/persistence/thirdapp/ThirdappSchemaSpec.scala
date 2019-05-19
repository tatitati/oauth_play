package infrastructure.test.persistence.thirdapp

import infrastructure.persistence.Exec
import infrastructure.persistence.thirdapp.ThirdappSchema
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery

class ThirdappSchemaSpec extends FunSuite with BeforeAndAfterEach with Exec {
  val thirdappSchema = TableQuery[ThirdappSchema]
  implicit val db = Database.forConfig("mydb")

  test("database forconfig type is:") {
    assert(db.isInstanceOf[Database])
  }

  test("thirdapp table exists") {
    val tables = exec(MTable.getTables).toList
    assert(tables.exists(_.name.name == "thirdapp") === true)
  }

  test("Can save one third app persistence model") {
    val persistentModel = BuildThirdappPersistentModel.anyNoPersisted()
    exec(thirdappSchema += persistentModel)
  }

  override def beforeEach() {
    exec(thirdappSchema.schema.dropIfExists)
    exec(thirdappSchema.schema.create)
  }
}


