package domain.model.user

import domain.model.IdentifiableInPersistence
import domain.model.user._
import com.github.nscala_time.time.Imports.DateTime

class User(
            val id: UserId, // is not the email as the user can change the email
            private var profile: UserProfile,
            val registeredDateTime: DateTime,
            private var emailConfirmed: Boolean,
            private var credentials: UserCredentials
   ) extends IdentifiableInPersistence {

  def equals(owner: User): Boolean = id.equals(owner.id)

  def isEmailConfirmed(): Boolean = emailConfirmed
  def getProfile: UserProfile = profile
  def getCredentials(): UserCredentials = credentials

  def updateFirstname(firstname: String): Unit = {
    profile = profile.copy(firstname = firstname)
  }

  def updateSurname(surname: String): Unit = {
    profile = profile.copy(surname = surname)
  }

  def updateEmail(email: String): Unit = {
    credentials = credentials.copy(email = email)
    emailConfirmed = false
  }

  def confirmEmail(): Unit = {
    emailConfirmed = true
  }

  def setDatebirth(datebirth: DateTime): Unit = {
    profile.copy(datebirth = datebirth)
  }
}
