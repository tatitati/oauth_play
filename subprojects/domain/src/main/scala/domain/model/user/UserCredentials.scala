package domain.model.user

case class UserCredentials(
    val email: String,
    val hashPassword: String
) {
  override def toString(): String = {
    s"""
       |      email: $email
       |      hashPassword: $hashPassword
    """.stripMargin
  }
}
