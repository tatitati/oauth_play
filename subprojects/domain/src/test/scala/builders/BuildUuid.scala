package test.domain.builders

import java.util.UUID

object BuildUuid {
  def uuidOne(): UUID = {
    UUID.fromString("47331985-9cd6-4632-b505-fceb476100a1")
  }

  def uuidTwo(): UUID = {
    UUID.fromString("b89a1ccb-18c9-41d0-bebc-48634b151991")
  }

  def any(): UUID = {
    UUID.randomUUID()
  }
}
