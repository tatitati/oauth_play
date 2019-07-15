package infrastructure.test.persistence.third

import domain.model.third.Third
import infrastructure.persistence.third.ThirdMapper
import org.scalatest.FunSuite

class ThirdMapperToDomainSpec extends FunSuite {

  test("Fields are mapped to domain") {
    val givenPersistent = BuildThirdPersistentModel.anyPersisted()

    val thenDomain = ThirdMapper.toDomain(givenPersistent)

    assert(thenDomain.isInstanceOf[Third])
  }

  test("Surrogate id is also mapped properly to domain") {
    val givenPersistent = BuildThirdPersistentModel.anyPersisted()

    assert(givenPersistent.id.isInstanceOf[Some[_]])

    val thenDomain = ThirdMapper.toDomain(givenPersistent)

    assert(thenDomain.isInstanceOf[Third])
    assert(thenDomain.getSurrogateId().isInstanceOf[Some[_]])
  }
}
