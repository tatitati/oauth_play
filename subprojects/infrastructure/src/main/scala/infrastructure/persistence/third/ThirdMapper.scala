package infrastructure.persistence.third

import java.util.UUID
import domain.model.third.{Third, ThirdId, ThirdProfile}

object ThirdMapper {

  def toDomain(fromPersistent: ThirdPersistentModel): Third = {
    val domain = Third(
      thirdId = ThirdId(
        value = UUID.fromString(fromPersistent.id)
      ),
      profile = ThirdProfile(
        name = fromPersistent.name,
        description = fromPersistent.description
      )
    )

    domain.setSurrogateId(fromPersistent.surrogateId)

    domain
  }

  def toPersistent(third: Third): ThirdPersistentModel= {
    ThirdPersistentModel(
      surrogateId = third.getSurrogateId(),
      id = third.thirdId.toString,
      name = third.getProfile.name,
      description = third.getProfile.description
    )
  }
}
