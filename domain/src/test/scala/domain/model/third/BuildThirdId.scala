package test.domain.model.third

import java.util.UUID

import domain.model.third.ThirdId
import test.domain.builders.BuildUuid

object BuildThirdId {

  def any(withValue: UUID = BuildUuid.any()): ThirdId = {
    ThirdId(withValue)
  }

  def specific1(): ThirdId = {
    any(withValue = BuildUuid.uuidOne())
  }

  def specific2(): ThirdId = {
    any(withValue = BuildUuid.uuidTwo())
  }
}