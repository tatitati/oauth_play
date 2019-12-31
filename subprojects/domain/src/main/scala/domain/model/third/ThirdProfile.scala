package domain.model.third

case class ThirdProfile(
    email: String,
    name: String,
    description: String
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
