package domain.model.third

case class ThirdProfile(
    val email: String
) {
  override def toString(): String = {
    s"""
       |   ThirdProfile
       |       email: $email
    """.stripMargin
  }
}
