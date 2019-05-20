package domain.model.user

case class UserId(value: String) {
  override def toString(): String = {
    value.toString
  }
}
