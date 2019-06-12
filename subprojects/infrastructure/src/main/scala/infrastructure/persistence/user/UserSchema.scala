package infrastructure.persistence.user

import com.github.nscala_time.time.Imports.DateTime
import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

class UserSchema(tag: Tag) extends Table[UserPersistentModel](tag, "user") {

  import infrastructure.persistence.CustomDateTimeToTimestamp._

  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def firstname = column[String]("firstname")
  def surname = column[String]("surname")
  def email = column[String]("email", O.SqlType("VARCHAR(255)"))
  def datebirth = column[Option[DateTime]]("datebirth", O.SqlType("DATETIME")) // this uses custom mapper type
  def username = column[String]("username")
  def salt = column[String]("salt", O.SqlType("VARCHAR(255)"))
  def hashPassword = column[String]("hashpassword", O.SqlType("VARCHAR(255)"))
  def registeredDateTime = column[DateTime]("registered_datetime", O.SqlType("DATETIME")) // this uses custom mapper type
  def emailconfirmed = column[Boolean]("email_confirmed")

  def * = (
    id,
    firstname,
    surname,
    email,
    datebirth,
    username,
    salt,
    hashPassword,
    registeredDateTime,
    emailconfirmed
  ).mapTo[UserPersistentModel]

  def idIndex = index("user____email____idx", email, unique=true)
}
