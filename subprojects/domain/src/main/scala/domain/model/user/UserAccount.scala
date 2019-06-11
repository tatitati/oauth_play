package domain.model.user

import com.github.nscala_time.time.Imports.DateTime

case class UserAccount (
  val email: String,
  val salt: String,
  val hashedPassword: String,
  val emailConfirmed: Boolean,
  val registeredDateTime: DateTime
) {
  override def toString(): String = {
    s"""
       |  UserAccount
       |       email: $email
       |       salt: $salt
       |       hashedPassword: $hashedPassword
       |       emailConfirmed: $emailConfirmed
       |       registeredDateTime: $registeredDateTime
    """.stripMargin
  }
}
