package domain.model.third

case class ThirdProfile(
    val email: String,
    val name: String,
    val description: String
) {
  override def toString(): String = {
    s"""
       |   ThirdProfile
       |       email: $email
       |       name: $name
       |       description: $description
    """.stripMargin
  }
}
