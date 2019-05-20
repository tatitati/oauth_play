package learning

import java.security.MessageDigest

import org.scalatest.FunSuite

class HashingSpec extends FunSuite {

  test("I can hash a simple string") {
    val text = "goodbyee"

    val hashed = hashtext(text)

    assert(hashed == "ba6c6f3caf96c7e7a764b5fbb8675d39013e1a8a077da5cd9ad45e2900114dcc")
  }

  test("The hash is always the same") {
    val text = "goodbyee"

    val hashed1 = hashtext(text)
    val hashed2 = hashtext(text)

    assert(hashed1 == hashed2)
  }

  test("The hash has a fixed length") {
    val text1 = "aa"
    val text2 = "abcdefghijklmn123456789ABCDEFGH_+"

    val hashed1 = hashtext(text1)
    val hashed2 = hashtext(text2)

    assert(hashed1 == "961b6dd3ede3cb8ecbaacbd68de040cd78eb2ed5889130cceb4c49268ea4d506")
    assert(hashed2 == "3cef5dc70c098666cf1039d153466634cdc0cdd5fb1f6274767ae6380bb3ad1c")
  }

  private def hashtext(text: String): String = {
    MessageDigest.getInstance("SHA-256")
      .digest(text.getBytes("UTF-8"))
      .map("%02x".format(_)).mkString
  }
}
