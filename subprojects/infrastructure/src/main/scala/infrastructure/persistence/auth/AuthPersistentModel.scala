package infrastructure.persistence.auth

import com.github.nscala_time.time.Imports._

case class AuthPersistentModel(
  val surrogateId: Option[Long] = None,
  val id: String,
  val thirdId: String,
  val userId: String,
  val scopeFirstName: Boolean,
  val scopeSurname: Boolean,
  val scopeEmail: Boolean,
  val tokenType: String,
  val tokenAccess: String,
  val tokenRefresh: String,
  val tokenExpiresIn: Int,
  val tokenGeneratedIn: DateTime
)
