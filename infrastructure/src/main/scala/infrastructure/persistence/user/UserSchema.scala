package infrastructure.persistence.user

import com.github.nscala_time.time.Imports.DateTime
import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

class UserSchema(tag: Tag) extends Table[UserPersistentModel](tag, "user") {

  import infrastructure.persistence.CustomDateTimeToTimestamp._

  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def firstname = column[String]("firstname")
  def surname = column[String]("surname")
  def datebirth = column[DateTime]("datebirth", O.SqlType("DATETIME")) // this uses custom mapper type
  def registeredDateTime = column[DateTime]("registered_datetime", O.SqlType("DATETIME")) // this uses custom mapper type
  def emailconfirmed = column[Boolean]("email_confirmed")
  def email = column[String]("email", O.SqlType("VARCHAR(255)"))
  def hashPassword = column[String]("hashpassword", O.SqlType("VARCHAR(255)"))

  def * = (
    id,
    firstname,
    surname,
    datebirth,
    registeredDateTime,
    emailconfirmed,
    email,
    hashPassword
  ).mapTo[UserPersistentModel]

  def idIndex = index("user____email____idx", email, unique=true)
}
