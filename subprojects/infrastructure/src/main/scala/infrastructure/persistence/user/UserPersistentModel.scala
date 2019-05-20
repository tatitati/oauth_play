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
)
