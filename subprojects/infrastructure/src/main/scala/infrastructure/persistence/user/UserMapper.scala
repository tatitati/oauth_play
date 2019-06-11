package infrastructure.persistence.user

import domain.model.user.{User, UserAccount, UserProfile}

object UserMapper {

  def toDomain(fromPersistent: UserPersistentModel): User = {
    val domain = new User(
        profile = UserProfile(
          firstname = fromPersistent.firstname,
          surname = fromPersistent.surname,
          datebirth = fromPersistent.datebirth,
          email = fromPersistent.email
        ),
        account = UserAccount(
          username = fromPersistent.username,
          salt = fromPersistent.salt,
          hashedPassword = fromPersistent.hashPassword,
          emailConfirmed = fromPersistent.emailConfirmed,
          registeredDateTime = fromPersistent.registeredDateTime,
        )
    )

    fromPersistent.id match {
      case None => throw new IllegalArgumentException("A persisted UserPersistentModel is expected to have a surrogate id in order to be mapped to domain")
      case Some(value) if value.isInstanceOf[Long] => domain.setSurrogateId(fromPersistent.id)
    }

    domain
  }

  def toPersistent(user: User): UserPersistentModel= {
    new UserPersistentModel(
      id = user.getSurrogateId(),
      firstname = user.getProfile.firstname,
      surname = user.getProfile.surname,
      email = user.getProfile.email,
      datebirth = user.getProfile.datebirth,
      username = user.getAccount.username,
      salt = user.getAccount.salt,
      hashPassword = user.getAccount.hashedPassword,
      registeredDateTime = user.getAccount.registeredDateTime,
      emailConfirmed = user.isEmailConfirmed()
    )
  }
}
