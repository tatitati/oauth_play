package domain.model.user

case class UserCredentials(
    val hashPassword: String
) {
  override def toString(): String = {
    s"""
       |      hashPassword: $hashPassword
    """.stripMargin
  }
}
