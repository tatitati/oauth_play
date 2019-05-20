package infrastructure.test.persistence.thirdapp

import domain.model.thirdapp.Thirdapp
import infrastructure.persistence.thirdapp.{ThirdappMapper, ThirdappPersistentModel}
import org.scalatest.FunSuite
import test.domain.model.thirdapp.BuildThirdapp

class ThirdappMapperSpec extends FunSuite {

  test("I can map from domain to persistent model") {
    val givenDomain = BuildThirdapp.any()

    val thenPersistent = ThirdappMapper.toPersistent(givenDomain)

    assert(thenPersistent.isInstanceOf[ThirdappPersistentModel])
  }

  test("can map from persistent to domain") {
    val givenPersistent = BuildThirdappPersistentModel.anyPersisted()

    val thenDomain = ThirdappMapper.toDomain(givenPersistent)

    assert(thenDomain.isInstanceOf[Thirdapp])
  }

  test("Surrogate id is set when mapping from persistent model") {
    val givenPersistent = BuildThirdappPersistentModel.anyPersisted()

    val thenDomain = ThirdappMapper.toDomain(givenPersistent)

    assert(thenDomain.getSurrogateId().isInstanceOf[Some[_]])
  }
}
