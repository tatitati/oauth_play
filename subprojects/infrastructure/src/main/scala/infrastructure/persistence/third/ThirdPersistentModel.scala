package infrastructure.persistence.third

import com.github.nscala_time.time.Imports.DateTime

case class ThirdPersistentModel(
   id: Option[Long] = None,
   email: String,
   username: String,
   name: String,
   description: String,
   salt: String,
   hashPassword: String,
   registeredDateTime: DateTime,
   emailconfirmed: Boolean
)
