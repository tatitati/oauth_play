package infrastructure.persistence.auth

import slick.jdbc.MySQLProfile.api._
import com.github.nscala_time.time.Imports.DateTime
import slick.lifted.Tag

class AuthSchema(tag: Tag) extends Table[AuthPersistentModel](tag, "auth") {

  import infrastructure.persistence.CustomDateTimeToTimestamp._

  def surrogateid = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

  def authId = column[String]("authid")
  def thirdid = column[String]("thirdid")
  def userid = column[String]("userid")

  def scopefirstname = column[Boolean]("scope_firstname")
  def scopesurname = column[Boolean]("scope_surname")
  def scopeemail = column[Boolean]("scope_email")

  def tokentype = column[String]("token_type")
  def tokenaccess = column[String]("token_access", O.SqlType("VARCHAR(255)"))
  def tokenrefresh = column[String]("token_refresh", O.SqlType("VARCHAR(255)"))
  def tokenexpiresin = column[Int]("token_expiresin")
  def tokengeneratedin = column[DateTime]("generated_in", O.SqlType("DATETIME"))

  def * = (
    surrogateid,
    authId,
    thirdid,
    userid,
    scopefirstname,
    scopesurname,
    scopeemail,
    tokentype,
    tokenaccess,
    tokenrefresh,
    tokenexpiresin,
    tokengeneratedin,
  ).mapTo[AuthPersistentModel]


  def tokenAccessIndex = index("auth____tokenaccess____idx", tokenaccess, unique=true)
  def refreshAccessIndex = index("auth____tokenrefresh____idx", tokenrefresh, unique=true)
}