package domain.model.third

import domain.model.IdentifiableInPersistence

case class Third(
      private var profile: ThirdProfile,
      private var account: ThirdAccount
  )
  extends IdentifiableInPersistence {

  def getThirdId: ThirdId = {
    ThirdId(account.username)
  }

  def equals(toThird: Third): Boolean = {
    getThirdId.equals(toThird.getThirdId)
  }

  def getProfile(): ThirdProfile = profile
  def getAccount(): ThirdAccount = account

  def updateEmail(toEmail: String) = {
    profile = profile.copy(email = toEmail)
    account = account.copy(emailConfirmed = false)
    this
  }

  def isEmailConfirmed(): Boolean = account.emailConfirmed

  def confirmEmail(): Third = {
    account = account.copy(emailConfirmed = true)
    this
  }

  override def toString(): String = {
    s"""
       |Third
       |====
       |  surrogateID: ${getSurrogateId()}
       |  ${account.toString()}
       |  ${profile.toString()}
    """.stripMargin
  }
}
