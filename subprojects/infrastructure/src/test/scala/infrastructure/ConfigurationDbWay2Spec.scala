package infrastructure

import infrastructure.persistence.Exec
import infrastructure.persistence.auth.AuthSchema
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

class ConfigurationDbWay2Spec extends FunSuite with BeforeAndAfterEach with Exec {
  val authSchema = TableQuery[AuthSchema]
  implicit val db = Database.forConfig("mydb")

  test("database forconfig type is:") {
    assert(db.isInstanceOf[Database])
  }

  override def beforeEach() {
    exec(authSchema.schema.dropIfExists)
    exec(authSchema.schema.create)
  }
}

