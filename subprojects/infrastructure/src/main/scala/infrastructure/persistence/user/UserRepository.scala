package infrastructure.persistence.user

import domain.model.user.User
import infrastructure.persistence.Connection
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import scala.concurrent.duration._
import scala.concurrent.Await

object UserRepository {
  val userSchema = TableQuery[UserSchema]
  implicit val dbConnection = Connection.getSingletonConnection("mydb")

  def save(user: User): Unit = {
    val userPersistent = UserMapper.toPersistent(user)

    dbConnection.run(userSchema += userPersistent)
  }

  def findByEmail(email: String): Option[User] = {
    val future = dbConnection.run(userSchema.filter(_.email === email).result)
    val rows = Await.result(future, 2.seconds)

    rows.size match {
      case 0 => None
      case _ => {
          val userPersistedModel = rows.head
          Some(UserMapper.toDomain(userPersistedModel))
      }
    }
  }

  def update(user: User) = {

    import infrastructure.persistence.CustomDateTimeToTimestamp._

    val persistentUser = UserMapper.toPersistent(user)

    val q = userSchema
      .filter(_.id === persistentUser.id)
      .map(
        user => (
          user.firstname,
          user.surname,
          user.datebirth,
          user.emailconfirmed,
          user.email
        )
      )

    val updateAction = q.update(
      persistentUser.firstname,
      persistentUser.surname,
      persistentUser.datebirth,
      persistentUser.isEmailConfirmed,
      persistentUser.email,
    )

    dbConnection.run(updateAction)
  }

  def existByEmail(email: String): Boolean = {
    val future = dbConnection.run(userSchema.filter(_.email === email).exists.result)
    Await.result(future, 2.seconds)
  }
}
