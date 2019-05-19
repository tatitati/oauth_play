package infrastructure.test.persistence.auth

import domain.model.auth.Auth
import test.domain.model.auth.BuildAuth
import infrastructure.persistence.auth.{AuthMapper, AuthPersistentModel}
import org.scalatest.FunSuite

class AuthMapperSpec extends FunSuite {

  test("Can map from domain to persistent") {
    val inDomain = BuildAuth.specific()

    val persistent = AuthMapper.toPersistent(inDomain)

    assert(persistent.isInstanceOf[AuthPersistentModel])
    assert(persistent.scopeEmail === true)
    assert(persistent.scopeSurname === false)
  }

  test("Can map from persistent to domain") {
    val persistent = BuildAuthPersistentModel.any()

    val inDomain = AuthMapper.toDomain(persistent)

    assert(inDomain.isInstanceOf[Auth])
    assert(inDomain.getSurrogateId() === None)

  }

  test("Can map surrogate properly") {
    val persistent = BuildAuthPersistentModel.any(withSurrogateId = Some(6))

    val inDomain = AuthMapper.toDomain(persistent)

    assert(inDomain.isInstanceOf[Auth])
    assert(inDomain.getSurrogateId() === Some(6))

  }
}
