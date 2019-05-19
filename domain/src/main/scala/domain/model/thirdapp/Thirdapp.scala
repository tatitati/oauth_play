package domain.model.thirdapp

import domain.model.IdentifiableInPersistence
import domain.model.third.ThirdId
import scala.util.Random

class Thirdapp(
                val thirdappId: ThirdappId,
                val thirdId: ThirdId,
                private var credentials: ThirdappCredentials,
                private var profile: ThirdappProfile)
  extends IdentifiableInPersistence {

  def getCredentials = credentials
  def getProfile = profile

  def refreshCredentials(): Unit = {
    credentials = ThirdappCredentials(
      clientId = makeRandomText(),
      clientSecret = makeRandomText()
    )
  }

  def updateName(name: String): Unit = {
    profile = profile.copy(name = name)
  }

  def updateUrlCallback(urlCallback: String): Unit = {
    profile = profile.copy(urlcallback = urlCallback)
  }

  def updateHomepage(homePage: String): Unit = {
    profile = profile.copy(homepage = homePage)
  }

  def updateDescription(description: String): Unit = {
    profile = profile.copy(description = description)
  }

  private def makeRandomText(length: Int = 10): String = {
    Random.alphanumeric take length mkString
  }
}
