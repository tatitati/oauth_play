package learning

import org.scalatest.FunSuite

import scala.util.matching.Regex

class RegexSpec extends FunSuite {

  test("I can match a simple regex expression") {
    assert("awesomepassword".matches("[0-9]") === false)
    assert("awesome324234234234password".matches(".*[0-9].*") === true)
  }

  test("I can extract some values from the match") {
    val numberPattern: Regex = "[0-9]".r

    assert(numberPattern.findFirstIn("awesomepassword") === None)
  }
}
