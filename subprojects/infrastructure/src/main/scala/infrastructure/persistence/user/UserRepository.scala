package infrastructure.persistence.user

import domain.model.user.User
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

object UserRepository {
  val userSchema = TableQuery[UserSchema]
  implicit val dbConnection = Database.forConfig("mydb")

  def save(user: User): Unit = {
    val userPersistent = UserMapper.toPersistent(user)

    dbConnection.run(userSchema += userPersistent)
  }
}
