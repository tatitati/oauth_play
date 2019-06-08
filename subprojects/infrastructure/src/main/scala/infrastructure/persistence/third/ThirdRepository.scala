package infrastructure.persistence.third

import domain.model.third.{Third, ThirdId}
import infrastructure.persistence.Connection
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import scala.concurrent.duration._
import scala.concurrent.Await

object ThirdRepository {
  val thirdSchema = TableQuery[ThirdSchema]
  implicit val dbConnection = Connection.getSingletonConnection("mydb")

  def save(third: Third): Unit = {
    val persistentModel = ThirdMapper.toPersistent(third)
    dbConnection.run(thirdSchema += persistentModel)
  }

  def findById(thirdId: ThirdId): Option[Third] = {
    val future = dbConnection.run(thirdSchema.filter(_.thirdId === thirdId.toString()).result)
    val rows = Await.result(future, 2.seconds)

    rows.length match {
      case 0 => None
      case _ => {
        val thirdPersisted = rows.head

        val third = ThirdMapper.toDomain(thirdPersisted)
        Some(third)
      }
    }
  }

  def exist(thirdId: ThirdId): Boolean = {
    val future = dbConnection.run(thirdSchema.filter(_.thirdId === thirdId.toString()).exists.result)
    Await.result(future, 2.seconds)
  }
}
