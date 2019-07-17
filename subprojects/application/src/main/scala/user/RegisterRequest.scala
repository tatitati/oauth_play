package application.user

case class RegisterRequest(
  val username: String,
  val email: String,
  val firstname: String,
  val surname: String,
  val password: String
)
