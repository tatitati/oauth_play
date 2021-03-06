package infrastructure.test.persistence.thirdapp

import infrastructure.test.persistence.Exec
import infrastructure.persistence.thirdapp.ThirdappSchema
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery

class ThirdappSchemaSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val thirdappSchema = TableQuery[ThirdappSchema]

  test("database forconfig type is:") {
    assert(dbConnection.isInstanceOf[Database])
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

  override def afterAll() = {
    dbConnection.close()
  }
}


