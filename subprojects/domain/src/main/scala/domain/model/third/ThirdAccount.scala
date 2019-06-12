package domain.model.third

import com.github.nscala_time.time.Imports.DateTime

case class ThirdAccount(
  val username: String,
  val salt: String,
  val hashedPassword: String,
  val emailConfirmed: Boolean,
  val registeredDateTime: DateTime
) {
  override def toString(): String = {
    s"""
       |  ThirdAccount
       |       username: $username
       |       salt: $salt
       |       hashedPassword: $hashedPassword
       |       emailConfirmed: $emailConfirmed
       |       registeredDateTime: $registeredDateTime
    """.stripMargin
  }
}
