package infrastructure.persistence.thirdapp

import domain.model.thirdapp.ThirdappCredentials
import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api._
import scala.concurrent.duration._
import scala.concurrent.Await
import domain.model.thirdapp.Thirdapp

object ThirdappRepository {

  val thirdappSchema = TableQuery[ThirdappSchema]
  implicit val dbConnection = Database.forConfig("mydb")

  def save(thirdapp: Thirdapp): Unit = {
    val persistentModel = ThirdappMapper.toPersistent(thirdapp)

    dbConnection.run(thirdappSchema += persistentModel)
  }

  def read(credentials: ThirdappCredentials): Option[Thirdapp] = {
    val future = dbConnection.run(thirdappSchema.filter(d => (d.clientid === credentials.clientId) && (d.clientsecret === credentials.clientSecret)).result)
    val rows = Await.result(future, 2.seconds)

    rows.size match {
      case 0 => None
      case _ => {
        val thirdappPersisted = rows.head
        val thirdapp = ThirdappMapper.toDomain(thirdappPersisted)
        Some(thirdapp)
      }
    }
  }

  def existByCredentials(credentials: ThirdappCredentials): Boolean = {
    val future = dbConnection.run(thirdappSchema.filter(d => (d.clientid === credentials.clientId) && (d.clientsecret === credentials.clientSecret)).exists.result)
    Await.result(future, 2.seconds)
  }
}
