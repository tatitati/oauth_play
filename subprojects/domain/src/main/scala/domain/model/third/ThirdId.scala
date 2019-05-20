package domain.model.third

import java.util.UUID

case class ThirdId(value: UUID) {
  override def toString(): String = {
    value.toString
  }
}
