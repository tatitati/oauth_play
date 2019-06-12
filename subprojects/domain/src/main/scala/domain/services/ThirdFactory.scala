package domain.services

import java.security.MessageDigest
import com.github.nscala_time.time.Imports.DateTime
import domain.model.third.{Third, ThirdAccount, ThirdProfile}
import scala.util.Random

object ThirdFactory {
  def create(
              withUsername: String,
              withFirstname: String,
              withSurname: String,
              withEmail: String,
              withPassword: String
            ): Third = {
    val salt = this.createSalt()

    new Third(
      ThirdProfile(
        email = withEmail
      ),
      ThirdAccount(
        username = withUsername,
        salt = salt,
        registeredDateTime = DateTime.now(),
        emailConfirmed = false,
        hashedPassword = hash(withPassword, salt)
      )
    )
  }

  private def createSalt(): String = {
    val value = for(i <- 1 to 10) yield { Random.nextPrintableChar() }
    value.mkString
  }

  private def hash(password: String, salt: String): String = {
    val text = password + salt

    MessageDigest.getInstance("SHA-256")
      .digest(text.getBytes("UTF-8"))
      .map("%02x".format(_)).mkString
  }
}
