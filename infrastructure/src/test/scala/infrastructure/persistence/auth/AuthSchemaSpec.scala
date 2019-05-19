package infrastructure.test.persistence.auth

import infrastructure.persistence.Exec
import infrastructure.persistence.auth.AuthSchema
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.jdbc.meta.MTable
import slick.lifted.TableQuery
import play.api.db.slick.DatabaseConfigProvider

class AuthSchemaSpec extends FunSuite with BeforeAndAfterEach with Exec {
  val authSchema = TableQuery[AuthSchema]
//  implicit val db = Database.forConfig("mydb")
  implicit val db = DatabaseConfigProvider.get[JdbcProfile]("mydb")(Play.current).db

  test("database forconfig type is:") {
    println(db)
    assert(db.isInstanceOf[Database])
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

