package infrastructure.test.persistence

import infrastructure.persistence.Connection
import slick.dbio.DBIO
import slick.jdbc.JdbcProfile

import scala.concurrent.Await
import scala.concurrent.duration._
import slick.jdbc.MySQLProfile.api._

trait Exec {
  implicit val dbConnection = Connection.getSingletonConnection("mydb")

  def exec[T](action: DBIO[T])(implicit db: JdbcProfile#Backend#Database): T =
  {
    val future = db.run(action)
    Await.result(future, 2.seconds)
  }
}
