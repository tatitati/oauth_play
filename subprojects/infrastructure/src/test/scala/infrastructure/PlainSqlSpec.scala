package infrastructure

import infrastructure.persistence.Exec
import infrastructure.persistence.auth.AuthSchema
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

class PlainSqlSpec extends FunSuite with BeforeAndAfterEach with Exec {
  val authSchema = TableQuery[AuthSchema]
  implicit val dbConnection = Connection.getSingletonConnection("mydb")

  test("Can run plain sql queries") {
    println("running.....")
    val action = sql"""SHOW STATUS WHERE `variable_name` = 'Threads_connected'""".as[(String, Int)].head
    val result = exec(action)
    dbConnection.close()

    // the assert at the end, because in case of error in the assert, the connection won't be closed
    assert(result === ("Threads_connected", 20))
  }

  override def beforeEach() {
    exec(authSchema.schema.dropIfExists)
    exec(authSchema.schema.create)
  }
}
