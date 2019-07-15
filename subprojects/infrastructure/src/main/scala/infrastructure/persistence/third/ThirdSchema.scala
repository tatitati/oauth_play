package infrastructure.persistence.third

import com.github.nscala_time.time.Imports.DateTime
import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

class ThirdSchema(tag: Tag) extends Table[ThirdPersistentModel](tag, "third") {
  import infrastructure.persistence.CustomDateTimeToTimestamp._

  def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def email = column[String]("email", O.SqlType("VARCHAR(255)"))
  def username = column[String]("username")
  def name = column[String]("name", O.SqlType("VARCHAR(255)"))
  def description = column[String]("description")
  def salt = column[String]("salt", O.SqlType("VARCHAR(255)"))
  def hashPassword = column[String]("hashpassword", O.SqlType("VARCHAR(255)"))
  def registeredDateTime = column[DateTime]("registered_datetime", O.SqlType("DATETIME")) // this uses custom mapper type
  def emailconfirmed = column[Boolean]("email_confirmed")

  def * = (
    id,
    email,
    username,
    name,
    description,
    salt,
    hashPassword,
    registeredDateTime,
    emailconfirmed
  ).mapTo[ThirdPersistentModel]

  def idIndex = index("third____email____idx", email, unique=true)
}
