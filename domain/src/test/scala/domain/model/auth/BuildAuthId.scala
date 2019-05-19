package test.domain.model.auth

import java.util.UUID

import domain.model.auth.AuthId
import test.domain.builders.BuildUuid

object BuildAuthId {

    def any(withValue: UUID = BuildUuid.any()): AuthId = {
        AuthId(withValue)
    }

    def specific1(): AuthId = {
      any(withValue = BuildUuid.uuidOne())
    }

    def specific2(): AuthId = {
      any(withValue = BuildUuid.uuidTwo())
    }
}
