package learning

import org.scalatest.FunSuite

import scala.util.matching.Regex

class FakerSpec extends FunSuite {
  // TODO: test a trait that select randomly elements from a Seq
  // TODO: put in another trait the random-string-generator
  test("I can use apply() to speed up process when coding") {


    val one = Faker("aa", "bb", "cc", "dd", "ee")
    val two = Faker("aa", "bb", "cc", "dd", "ee")
    val three = Faker("aa", "bb", "cc", "dd", "ee")

    assert(one !== two !== three, "=> Should be random (they might match also sometimes...because of that is random)")
    assert(one.isInstanceOf[String] && two.isInstanceOf[String] && three.isInstanceOf[String])
  }

  test("I can generate random strings") {
    val one = Faker.text()
    val two = Faker.text()
    val three = Faker.text()

    assert(one !== two !== three)
    assert(one.isInstanceOf[String] && two.isInstanceOf[String] && three.isInstanceOf[String])
  }

  test("random text return printable characters") {
    val numberPattern: Regex = "[0-9a-zA-Z()#\\-_=?+.:]".r

    val one = Faker.text()
    assert(numberPattern.findFirstIn(one).isInstanceOf[Some[_]])
  }
}
