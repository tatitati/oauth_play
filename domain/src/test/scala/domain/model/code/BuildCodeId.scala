package test.domain.model.code

import java.util.UUID

import domain.model.code.CodeId
import test.domain.builders.BuildUuid

object BuildCodeId {

  def any(withValue: UUID = BuildUuid.any()): CodeId = {
    CodeId(withValue)
  }

  def specific1(): CodeId = {
    any(withValue = BuildUuid.uuidOne())
  }

  def specific2(): CodeId = {
    any(withValue = BuildUuid.uuidTwo())
  }
}
