package infrastructure

import slick.jdbc
import slick.jdbc.MySQLProfile.api._

object Connection {

  private var connection: Option[jdbc.MySQLProfile.backend.Database] = None

  def getSingletonConnection(nameConfig: String): jdbc.MySQLProfile.backend.Database = {
    connection match {
      case None => {
        val conn = Some(Database.forConfig(nameConfig))
        conn.get
      }
      case _ => connection.get
    }

  }
}
