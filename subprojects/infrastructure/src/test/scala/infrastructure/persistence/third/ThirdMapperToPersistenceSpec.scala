package infrastructure.test.persistence.third

import infrastructure.persistence.third.{ThirdMapper, ThirdPersistentModel}
import org.scalatest.FunSuite
import test.domain.model.third.BuildThird

class ThirdMapperToPersistenceSpec extends FunSuite {

  test("ThirdDomain -> ThirdPersistentModel produce a ThirdPersistentModel") {
    val givenThirdDomain = BuildThird.any()
    val thenPersistent = ThirdMapper.toPersistent(givenThirdDomain)

    assert(thenPersistent.isInstanceOf[ThirdPersistentModel])
  }

  test("ThirdDomain -> ThirdPersistentModel gives the expected ThirdPersistentModel") {
    val givenThirdDomain = BuildThird.any(
      withSurrogateId = None
    )

    val thenPersistent = ThirdMapper.toPersistent(givenThirdDomain)

    assert(thenPersistent.id === None)
  }

  test("Surrogate id is mapped to the proper value in persistent model") {
    val givenThirdDomain = BuildThird.any(withSurrogateId = Some(6))
    val persistent = ThirdMapper.toPersistent(givenThirdDomain)

    assert(persistent.id === Some(6))
  }
}
