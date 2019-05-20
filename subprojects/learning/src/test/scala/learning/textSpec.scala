package learning

import org.scalatest.FunSuite

class textSpec extends FunSuite {
  test("I can convert multiline to singleline") {
    val text ="""this
        |is
        |whatever"""

    val text1 = text.stripMargin.replaceAll("\n", " ")
    val text2 = text.stripMargin.replaceAll("\n", "")

    assert(text1 === "this is whatever")
    assert(text2 === "thisiswhatever")

  }

  test("Without striping margins") {
    val text ="""this
                |is
                |whatever""".replaceAll("\n", " ")


    assert(text === "this                 |is                 |whatever")
  }

  test("Without using pipes") {
    val text ="""this
                is
                whatever""".stripMargin


    assert(text === "this\n                is\n                whatever")
  }


}
