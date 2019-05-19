package domain.model

abstract class IdentifiableInPersistence {
  private var surrogateId: Option[Long] = None

  def setSurrogateId(withSurrogateId: Option[Long]): Unit = {
    if(surrogateId != None) {
      throw new IllegalArgumentException("Surrogate ID cannot be modified once that is set")
    }

    if(withSurrogateId == None) {
      throw new IllegalArgumentException("Surrogate ID cannot be initialized to None")
    }

    surrogateId = withSurrogateId
  }

  def getSurrogateId(): Option[Long] = {
    surrogateId
  }
}
