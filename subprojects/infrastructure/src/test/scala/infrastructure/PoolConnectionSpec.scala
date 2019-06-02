package infrastructure.test.persistence

import infrastructure.persistence.auth.AuthSchema
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

class PoolConnectionSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  test("Can run plain sql queries") {
    val action = sql"""SHOW STATUS WHERE `variable_name` = 'Threads_connected'""".as[(String, Int)].head
    val result = exec(action)
    println(result)

    // the assert at the end, because in case of error in the assert, the connection won't be closed
    //assert(result === ("Threads_connected", 20))
  }

  test("Create another connection") {
    val action = sql"""SHOW STATUS WHERE `variable_name` = 'Threads_connected'""".as[(String, Int)].head
    val result = exec(action)
    println(result)

    // the assert at the end, because in case of error in the assert, the connection won't be closed
    //assert(result === ("Threads_connected", 20))
  }

  test("Create another connection2") {
    val action = sql"""SHOW STATUS WHERE `variable_name` = 'Threads_connected'""".as[(String, Int)].head
    val result = exec(action)
    println(result)

    // the assert at the end, because in case of error in the assert, the connection won't be closed
    //assert(result === ("Threads_connected", 20))
  }

  test("Create another connection3") {
    val action = sql"""SHOW STATUS WHERE `variable_name` = 'Threads_connected'""".as[(String, Int)].head
    val result = exec(action)
    println(result)

    // the assert at the end, because in case of error in the assert, the connection won't be closed
    //assert(result === ("Threads_connected", 20))
  }

  test("Create another connection4") {
    val action = sql"""SHOW STATUS WHERE `variable_name` = 'Threads_connected'""".as[(String, Int)].head
    val result = exec(action)
    println(result)


    // the assert at the end, because in case of error in the assert, the connection won't be closed
    //assert(result === ("Threads_connected", 20))
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
