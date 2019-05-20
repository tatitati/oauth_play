package learning

import org.scalatest.FunSuite

class SurrogateIdSpec extends FunSuite {

  abstract class MyAbstract {
    private var persistentSurrogateId: String = _

    def setSurrogateId(withSurrogateId: String): Unit = {

      if (persistentSurrogateId != null) {
        throw new IllegalArgumentException("Surrogate ID cannot be modified once that is set")
      }

      persistentSurrogateId = withSurrogateId
    }

    protected def getSurrogateId(): String = {
      persistentSurrogateId
    }
  }

  class MyConcret(age: Int) extends MyAbstract {

    def display(): String = {
      s"Result: ${age} = ${getSurrogateId()}"
    }
  }

  test("I don't need to pass any extra parameter to the " +
    "MyConcret constructor, and in this way I can hide better the Surrogate ID") {
      val a = new MyConcret(age=32)

      // I prefer this way because it keeps clean each DOMAIN model constructor. So I can develop
      // the domain easily, with no noise of persistence
      a.setSurrogateId(withSurrogateId = "a first name")
      assert(a.display() === "Result: 32 = a first name")
  }

  test("I cannot assign twice the surrogate id") {
    var a = new MyConcret(age=32)
    a.setSurrogateId(withSurrogateId = "a first name")

    val thrown = intercept[IllegalArgumentException] {
      a.setSurrogateId(withSurrogateId = "a second name")
    }
    assert(thrown.getMessage() === "Surrogate ID cannot be modified once that is set")
  }
}
