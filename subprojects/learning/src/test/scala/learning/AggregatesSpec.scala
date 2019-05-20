package learning

import org.scalatest.FunSuite

class AggregatesSpec extends FunSuite {

  class Image(var title: String)
  class IdCard(var photo: Image)


  test("Inner entities can be updated from outside, what SHOULDNT HAPPEN ever") {
    var myidcard = new IdCard(
      new Image("title of my image")
    )

    assert(myidcard.photo.title === "title of my image")

    myidcard.photo.title = "new modified title from outside"

    assert(myidcard.photo.title === "new modified title from outside")
  }

  test("Objects are passed by reference, so even using different variables, the aggregate is still updated from outside, THIS SHOULDNT HAPPEN EVER!") {
    var myidcard = new IdCard(
      new Image("title of my image")
    )

    assert(myidcard.photo.title === "title of my image")

    var photo = myidcard.photo
    photo.title = "new modified title from outside"

    assert(myidcard.photo.title === "new modified title from outside")
  }
}
