package domain.model.user

import domain.model.IdentifiableInPersistence
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

  def updateFirstname(firstname: String): User = {
    profile = profile.copy(firstname = firstname)
    this
  }

  def updateSurname(surname: String): User = {
    profile = profile.copy(surname = surname)
    this
  }

  def updateEmail(email: String): User = {
    credentials = credentials.copy(email = email)
    emailConfirmed = false
    this
  }

  def confirmEmail(): User = {
    emailConfirmed = true
    this
  }

  def setDatebirth(datebirth: DateTime): User = {
    profile.copy(datebirth = datebirth)
    this
  }

  override def toString(): String = {
    s"""
       |User
       |====
       |   registeredDateTime: ${registeredDateTime.toString}
       |   emailConfirmed: ${emailConfirmed.toString()}
       |   profile: ${profile.toString()}
       |   credentials: ${credentials.toString()}
    """.stripMargin
  }
}
