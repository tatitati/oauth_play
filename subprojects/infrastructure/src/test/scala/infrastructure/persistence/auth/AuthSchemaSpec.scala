package infrastructure.test.persistence.auth

import infrastructure.Connection
import infrastructure.persistence.Exec
import infrastructure.persistence.auth.AuthSchema
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery

class AuthSchemaSpec extends FunSuite with BeforeAndAfterEach with Exec {
  val authSchema = TableQuery[AuthSchema]
  implicit val dbConnection = Connection.getSingletonConnection("mydb")

  test("database forconfig type is:") {
    assert(dbConnection.isInstanceOf[Database])
  }

  test("auth table exists") {
    val tables = exec(MTable.getTables).toList
    assert(tables.exists(_.name.name == "auth") === true)
  }

  test("Can save one owner profile persistence model") {
    val persistentModel = BuildAuthPersistentModel.any()
    exec(authSchema += persistentModel)
  }

  override def beforeEach() {
    exec(authSchema.schema.dropIfExists)
    exec(authSchema.schema.create)
  }
}

