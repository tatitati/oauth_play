package infrastructure.persistence.user

import com.github.nscala_time.time.Imports.DateTime

case class UserPersistentModel(
  id: Option[Long] = None,
  firstname: String,
  surname: String,
  datebirth: DateTime,
  registeredDateTime: DateTime,
  isEmailConfirmed: Boolean,
  email: String,
  hashPassword: String
) {
  override def toString(): String = {
    s"""
      |UserPersistentModel
      |===================
      |   id: ${id.toString}
      |   firstname: $firstname,
      |   surname: $surname,
      |   datebirth: ${datebirth.toString},
      |   registeredDateTime: ${registeredDateTime.toString},
      |   isEmailConfirmed: ${isEmailConfirmed.toString},
      |   email: $email,
      |   hashPassword: $hashPassword
    """.stripMargin
  }
}


