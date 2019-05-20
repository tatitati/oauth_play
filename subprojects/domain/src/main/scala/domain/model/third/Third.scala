package domain.model.third

import domain.model.IdentifiableInPersistence
import scala.util.Random

case class Third(
                  val thirdId: ThirdId,
                  private var profile: ThirdProfile)
  extends IdentifiableInPersistence {

  def getProfile: ThirdProfile = profile
  def equals(third: Third): Boolean = {
    this.thirdId.equals(third.thirdId)
  }

  def updateName(withName: String): Unit = {
    profile = profile.copy(name = withName)
  }

  def updateDescription(withDescription: String): Unit = {
    profile = profile.copy(description = withDescription)
  }

  private def makeRandomText(length: Int = 10): String = {
    Random.alphanumeric take length mkString
  }
}
