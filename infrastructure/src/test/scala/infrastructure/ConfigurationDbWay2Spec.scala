package infrastructure

import infrastructure.persistence.Exec
import infrastructure.persistence.auth.AuthSchema
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery


class ConfigurationDbWay2Spec extends FunSuite with BeforeAndAfterEach with Exec {
  val authSchema = TableQuery[AuthSchema]

  implicit val db = Database.forConfig("mydb")

  //implicit val db = DatabaseConfigProvider.get[JdbcProfile]("mydb")(Play.current).db

  test("database forconfig type is:") {
    println(db)
    assert(db.isInstanceOf[Database])
  }

  override def beforeEach() {
    exec(authSchema.schema.dropIfExists)
    exec(authSchema.schema.create)
  }
}

