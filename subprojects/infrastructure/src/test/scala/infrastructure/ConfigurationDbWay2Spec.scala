package infrastructure.test.persistence

import infrastructure.persistence.auth.AuthSchema
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

class ConfigurationDbWay2Spec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val authSchema = TableQuery[AuthSchema]

  test("database forconfig type is:") {
    assert(dbConnection.isInstanceOf[Database])
  }

  override def beforeEach() {
    exec(authSchema.schema.dropIfExists)
    exec(authSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}

