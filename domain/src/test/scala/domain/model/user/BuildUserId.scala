package test.domain.model.user

import domain.model.user.UserId
import scala.util.Random

object BuildUserId {

  def any(withValue: String = randomText()): UserId = {
    UserId(withValue)
  }

  def specific1(): UserId = {
    UserId("user id one")
  }

  def specific2(): UserId = {
    UserId("user id one")
  }

  private def randomText(length: Int = 10): String = {
    val value = for(i <- 1 to length) yield { Random.nextPrintableChar() }
    value.mkString
  }
}
