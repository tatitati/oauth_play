package infrastructure.persistence.user

import domain.model.user.User
import infrastructure.persistence.third.ThirdMapper
import infrastructure.persistence.third.ThirdRepository.{db, thirdSchema}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import scala.concurrent.duration._

import scala.concurrent.Await

object UserRepository {
  val userSchema = TableQuery[UserSchema]
  implicit val dbConnection = Database.forConfig("mydb")

  def save(user: User): Unit = {
    val userPersistent = UserMapper.toPersistent(user)

    dbConnection.run(userSchema += userPersistent)
  }

  def findByEmail(email: String): Option[User] = {
    val future = db.run(userSchema.filter(_.email === email).result)
    val rows = Await.result(future, 2.seconds)

    rows.size match {
      case 0 => None
      case _ => {
          val userPersistedModel = rows.head
          Some(UserMapper.toDomain(userPersistedModel))
      }
    }
  }

  def existByEmail(email: String): Boolean = {
    val future = db.run(userSchema.filter(_.email === email).exists.result)
    Await.result(future, 2.seconds)
  }
}