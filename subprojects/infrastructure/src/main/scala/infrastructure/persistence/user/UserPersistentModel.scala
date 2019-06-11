package infrastructure.persistence.user

import com.github.nscala_time.time.Imports.DateTime

case class UserPersistentModel(
    id: Option[Long] = None,
    firstname: String,
    surname: String,
    email: String,
    datebirth: Option[DateTime],
    username: String,
    salt: String,
    hashPassword: String,
    registeredDateTime: DateTime,
    emailConfirmed: Boolean
) {
  override def toString(): String = {
    s"""
      |UserPersistentModel
      |===================
      |   id: ${id.toString}
      |   firstname: $firstname
      |   surname: $surname
      |   email: $email,
      |   datebirth: ${datebirth.toString}
      |   username: $username
      |   salt: $salt
      |   hashPassword: $hashPassword
      |   registeredDateTime: ${registeredDateTime.toString}
      |   emailConfirmed: ${emailConfirmed.toString}
    """.stripMargin
  }
}


