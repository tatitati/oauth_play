//package infrastructure.test.persistence
//
//import com.zaxxer.hikari.pool.HikariProxyConnection
//import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
//import play.api.db.Databases
//
//class DatabaseSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll {
//
//  test("test type database") {
//    val database = Databases(
//      driver = "com.mysql.jdbc.Driver",
//      url = "jdbc:mysql://service_db:3306/play_db?useSSL=false",
//      config = Map(
//        "username" -> "root",
//        "password" -> ""
//      )
//    )
////    val dbConnection = database.getConnection()
//
//    assert(database.isInstanceOf[play.api.db.PooledDatabase])
//  }
//
//  test("test type connection") {
//    val database = Databases(
//      driver = "com.mysql.jdbc.Driver",
//      url = "jdbc:mysql://service_db:3306/play_db?useSSL=false",
//      config = Map(
//        "username" -> "root",
//        "password" -> ""
//      )
//    )
//    val dbConnection = database.getConnection()
//
//    assert(dbConnection.isInstanceOf[HikariProxyConnection])
//  }
//
//  test("I can run a query writing flat sql statements") {
//
//  }
//}
