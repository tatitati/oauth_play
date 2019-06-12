package domain.model.user

import domain.model.IdentifiableInPersistence
import com.github.nscala_time.time.Imports.DateTime

class User(
        private var profile: UserProfile,
        private var account: UserAccount
   ) extends IdentifiableInPersistence {

  def getUserId: UserId = {
    UserId(account.username)
  }

  def equals(toUser: User): Boolean = {
    getUserId.equals(toUser.getUserId)
  }

  def getProfile: UserProfile = profile
  def getAccount: UserAccount = account

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
    account = account.copy(emailConfirmed = false)
    this
  }

  def updateDateBirth(datebirth: Option[DateTime]): User = {
    profile = profile.copy(datebirth = datebirth)
    this
  }

  def isEmailConfirmed(): Boolean = account.emailConfirmed

  def confirmEmail(): User = {
    account = account.copy(emailConfirmed = true)
    this
  }

  override def toString(): String = {
    s"""
       |User
       |====
       |  surrogateID: ${getSurrogateId()}
       |  ${account.toString()}
       |  ${profile.toString()}
    """.stripMargin
  }
}
