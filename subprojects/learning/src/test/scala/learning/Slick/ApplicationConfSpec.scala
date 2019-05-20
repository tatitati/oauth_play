package test.app.learning.Slick

import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.FunSuite
import slick.jdbc.H2Profile.api._

class ApplicationConfSpec extends FunSuite {

  test("System find application.conf and find some configuration keys") {
    val value = ConfigFactory.load()

    assert(value.isInstanceOf[Config])
    assert(value.hasPath("akka") === true)
    assert(value.hasPath("akka.actor") === true)
    assert(value.hasPath("h2mem1") === true, "h2mem1 should be found in application.conf")
    assert(value.hasPath("h2mem1.connectionPool") === true, "h2mem1.connectionPool should be found in application.conf")
    assert(value.hasPath("h2mem1.url") === true, "h2mem1.url should be found in application.conf")
//    assert(value.hasPath("mydb") === true, "mydb should be found in application.conf")
    assert(value.hasPath("slick") === true, "slick should be found in application.conf")
  }

  test("System find application.conf and some slick values are correct") {
    val value = ConfigFactory.load()

    assert(value.getString("h2mem1.url") === "jdbc:h2:mem:test1", "h2mem1.url should be equals to: jdbc:h2:mem:test1")
    assert(value.getString("h2mem1.driver") === "org.h2.Driver", "h2mem1.driver should be equals to: org.h2.Driver")
  }

  test("Slick database configuration can be loaded in a different way as well") {
    val db = Database.forConfig("h2mem1")
    assert(db.isInstanceOf[Database])
  }
}
