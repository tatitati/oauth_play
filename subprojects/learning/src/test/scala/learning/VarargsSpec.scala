package learning

import org.scalatest.FunSuite

import scala.collection.mutable

class VarargsSpec extends FunSuite {

  private def dumbfunction(numbers: Int*) = {
    numbers
  }

  test("A function using varargs (undefined amount of variables) conver the list of items into a Seq automatically") {
    val result = dumbfunction(22, 33, 44, 55)

    assert(result.isInstanceOf[Seq[Int]])
    assert(result.isInstanceOf[mutable.WrappedArray[Int]])
  }
}
