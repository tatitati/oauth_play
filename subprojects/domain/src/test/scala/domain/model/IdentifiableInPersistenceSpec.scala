package test.domain.model

import domain.model.IdentifiableInPersistence
import org.scalatest.FunSuite

class IdentifiableInPersistenceSpec extends FunSuite {

    class Concrete extends IdentifiableInPersistence {
      def getSurroId(): Option[Long] = {
        getSurrogateId()
      }
    }

    test("By default the surrogate-id is None") {
      val a = new Concrete()

      assert(a.getSurroId().isInstanceOf[Option[Long]])
      assert(a.getSurroId() == None)
    }

  test("I can set a surrogate-id") {
    val a = new Concrete()

    a.setSurrogateId(withSurrogateId = Some(23))
    assert(a.getSurroId() === Some(23))
  }

  test("A surrogate is immutable once that is set") {
    val a = new Concrete()

    a.setSurrogateId(withSurrogateId = Some(23))


    val thrown = intercept[IllegalArgumentException] {
      a.setSurrogateId(withSurrogateId = Some(24))
    }
    assert(thrown.getMessage() === "Surrogate ID cannot be modified once that is set")
  }

  test("I cannot set NONE") {
    val a = new Concrete()

    val thrown = intercept[IllegalArgumentException] {
      a.setSurrogateId(withSurrogateId = None)
    }
    assert(thrown.getMessage() === "Surrogate ID cannot be initialized to None")
  }
}