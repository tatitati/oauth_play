package infrastructure.persistence

import slick.jdbc
import slick.jdbc.MySQLProfile.api._

object Connection {

  private var connection: Option[jdbc.MySQLProfile.backend.Database] = None

  def getSingletonConnection(nameConfig: String): jdbc.MySQLProfile.backend.Database = {
    connection match {
      case None => {
//        val conn = Some(Database.forConfig(nameConfig))
        val conn = Some(Database.forURL(
          "jdbc:mysql://service_db:3306/play_db?useSSL=false",
          driver="com.mysql.jdbc.Driver",
          user = "root",
          password = "",
          executor = AsyncExecutor(
            "test1",
            numThreads=10,
            queueSize=1000
          )
        ))
        conn.get
      }
      case _ => connection.get
    }

  }
}