package test.domain.model.thirdapp

import java.util.UUID

import domain.model.thirdapp.ThirdappId
import test.domain.builders.BuildUuid

object BuildThirdappId {
  def any(withValue: UUID = BuildUuid.any()): ThirdappId = {
    ThirdappId(withValue)
  }

  def specific1(): ThirdappId = {
    any(withValue = BuildUuid.uuidOne())
  }

  def specific2(): ThirdappId = {
    any(withValue = BuildUuid.uuidTwo())
  }
}
