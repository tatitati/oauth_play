package infrastructure.test.persistence.third

import infrastructure.persistence.Exec
import infrastructure.persistence.third.ThirdSchema
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery

class ThirdSchemaSpec extends FunSuite with BeforeAndAfterEach with Exec {
  val thirdSchema = TableQuery[ThirdSchema]
  implicit val db = Database.forConfig("mydb")

  test("database forconfig type is:") {
    assert(db.isInstanceOf[Database])
  }

  test("third table exists") {
    val tables = exec(MTable.getTables).toList
    assert(tables.exists(_.name.name == "third") === true)
  }

  test("Can save one owner profile persistence model") {
    val persistentModel = BuildThirdPersistentModel.anyNoPersisted()
    exec(thirdSchema += persistentModel)
  }

  override def beforeEach() {
    exec(thirdSchema.schema.dropIfExists)
    exec(thirdSchema.schema.create)
  }
}

