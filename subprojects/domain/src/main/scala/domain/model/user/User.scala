package domain.model.user

import domain.model.{SurrogateIdInPersistence, IdentifiableInPersistence}
import com.github.nscala_time.time.Imports.DateTime

class User(
            val id: UserId, // is not the email as the user can change the email
            private var profile: UserProfile,
            val registeredDateTime: DateTime,
            private var emailConfirmed: Boolean,
   ) extends IdentifiableInPersistence with SurrogateIdInPersistence {

  def equals(toUser: User): Boolean = id.equals(toUser.id)

  def isEmailConfirmed(): Boolean = emailConfirmed
  def getProfile: UserProfile = profile

  def updateFirstname(firstname: String): User = {
    profile = profile.copy(firstname = firstname)
    this
  }

  def updateSurname(surname: String): User = {
    profile = profile.copy(surname = surname)
    this
  }

  def updateEmail(email: String): User = {
    profile = profile.copy(email = email)
    emailConfirmed = false
    this
  }

  def updateDateBirth(datebirth: Option[DateTime]): User = {
    profile = profile.copy(datebirth = datebirth)
    this
  }

  def confirmEmail(): User = {
    emailConfirmed = true
    this
  }

  override def toString(): String = {
    s"""
       |User
       |====
       |   surrogateID: ${getSurrogateId()}
       |   hashCredentials: ${getHashcredentials()}
       |   registeredDateTime: ${registeredDateTime.toString}
       |   emailConfirmed: ${emailConfirmed.toString()}
       |   profile: ${profile.toString()}
    """.stripMargin
  }
}
