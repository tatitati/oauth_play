package domain.model.code

import java.util.UUID

case class CodeId(value: UUID) {
  override def toString(): String = {
    value.toString
  }
}
