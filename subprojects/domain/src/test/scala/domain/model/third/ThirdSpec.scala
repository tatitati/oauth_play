package test.domain.model.third

import domain.model.third.ThirdId
import org.scalatest.FunSuite

class ThirdSpec extends FunSuite {

  test("Identifier is the proper type") {
    val third = BuildThird.any()

    assert(third.thirdId.isInstanceOf[ThirdId] === true)
  }

  test("Equals use the key to compare") {
    val third1 = BuildThird.any(withId = BuildThirdId.specific1())
    val third2 = BuildThird.any(withId = BuildThirdId.specific2())
    val third3 = BuildThird.any(withId = BuildThirdId.specific2())

    assert(third1.equals(third2) === false)
    assert(third2.equals(third3) === true)
  }

  test("can edit third basic profile") {
    val givenThird = BuildThird.any(
      withThirdProfile = BuildThirdProfile.any(
        withName = "first name"
      )
    )

    givenThird.updateName("second name")

    assert(givenThird.getProfile.name === "second name")
  }
}
