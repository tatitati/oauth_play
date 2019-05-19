package domain.model.auth

import java.util.UUID

case class AuthId(value: UUID) {
  override def toString(): String = {
    value.toString
  }
}
