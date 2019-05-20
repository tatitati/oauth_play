package domain.model.thirdapp

import java.util.UUID

case class ThirdappId(value: UUID) {
  override def toString(): String = {
    value.toString
  }
}
