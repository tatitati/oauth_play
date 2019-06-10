package domain.model

trait SurrogateIdInPersistence {
  private var hashCredentials: String = ""

  def setHashcredentials(withHashCredentials: String): Unit = {
    if(hashCredentials != "") {
      throw new IllegalArgumentException("Hash credentials cannot be modified once that is set")
    }

    if(withHashCredentials == "") {
      throw new IllegalArgumentException("Hash credentials cannot be initialized to empty string")
    }

    hashCredentials = withHashCredentials
  }

  def getHashcredentials(): String = {
    hashCredentials
  }
}
