package infrastructure.persistence.third

import domain.model.third.{Third, ThirdAccount, ThirdProfile}

object ThirdMapper {

  def toDomain(fromPersistent: ThirdPersistentModel): Third = {
    val domain = Third(
      profile = ThirdProfile(
        email = fromPersistent.email,
        name = fromPersistent.name,
        description = fromPersistent.description
      ),
      account = ThirdAccount(
        username = fromPersistent.username,
        salt = fromPersistent.salt,
        hashedPassword = fromPersistent.hashPassword,
        emailConfirmed = fromPersistent.emailconfirmed,
        registeredDateTime = fromPersistent.registeredDateTime
      )
    )

    domain.setSurrogateId(fromPersistent.id)

    domain
  }

  def toPersistent(third: Third): ThirdPersistentModel= {
    ThirdPersistentModel(
      id = third.getSurrogateId(),
      email = third.getProfile.email,
      username = third.getAccount().username,
      name = third.getProfile.name,
      description = third.getProfile.description,
      salt = third.getAccount().salt,
      hashPassword = third.getAccount().hashedPassword,
      registeredDateTime = third.getAccount().registeredDateTime,
      emailconfirmed = third.getAccount().emailConfirmed
    )
  }
}
