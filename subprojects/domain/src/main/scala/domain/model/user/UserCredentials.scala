package domain.model.user

case class UserCredentials(
    val email: String,
    val hashPassword: String
)
