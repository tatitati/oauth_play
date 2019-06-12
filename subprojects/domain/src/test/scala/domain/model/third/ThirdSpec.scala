package test.domain.model.third

import domain.model.third.{BuildThirdAccount, Third, ThirdId}
import org.scalatest.FunSuite

class ThirdSpec extends FunSuite {

  test("Identifier is the proper type") {
    val third = BuildThird.any()

    assert(third.isInstanceOf[Third])
    assert(third.getThirdId.isInstanceOf[ThirdId])
  }

  test("Equals use the key to compare") {
    val third1 = BuildThird.any(
      withAccount = BuildThirdAccount.any(
        withUsername = "username1"
      )
    )

    val third2 = BuildThird.any(
      withAccount = BuildThirdAccount.any(
        withUsername = "username1"
      )
    )

    val third3 = BuildThird.any(
      withAccount = BuildThirdAccount.any(
        withUsername = "username2"
      )
    )

    assert(third1.equals(third2) === true)
    assert(third2.equals(third3) === false)
  }

  test("can edit third basic profile") {
    val givenThird = BuildThird.any(
      withProfile = BuildThirdProfile.any(
        withEmail = "an_email@whatever.com"
      )
    )

    assert(givenThird.getProfile().email === "an_email@whatever.com")
    givenThird.updateEmail("different_email@whatever.com")
    assert(givenThird.getProfile().email === "different_email@whatever.com")
  }
}
