package infrastructure.persistence.user

import domain.model.user.User
import infrastructure.persistence.Connection
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import scala.concurrent.duration._
import scala.concurrent.Await

class UserRepository {
  val userSchema = TableQuery[UserSchema]
  implicit val dbConnection = Connection.getSingletonConnection("mydb")

  def create(user: User): Unit = {
    //Todo: throw exception if the user has already a surrogate id.
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
      persistentUser.emailConfirmed,
      persistentUser.email,
    )

    dbConnection.run(updateAction)
  }

  def existEmail(email: String): Boolean = {
    val future = dbConnection.run(userSchema.filter(_.email === email).exists.result)
    Await.result(future, 2.seconds)
  }

  def existUsername(username: String): Boolean = {
    val future = dbConnection.run(userSchema.filter(_.username === username).exists.result)
    Await.result(future, 2.seconds)
  }
}
