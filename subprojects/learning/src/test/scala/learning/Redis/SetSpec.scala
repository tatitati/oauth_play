package test.app.learning.Redis

import com.redis.RedisClient
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}

class SetSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll {

  val red = new RedisClient(
    sys.env.get("CONTAINER_REDIS_PORT_6379_TCP_ADDR").get,
    6379
  )

  test("Can set key-values") {
      red.set("mykey", "my value")

      val readValue = red.get("mykey")

      assert(readValue === Some("my value"))
  }

  test("redis is wiped-out for the start of each test") {
    val readValue = red.get("mykey")

    assert(readValue === None)
  }

  test("Can set expiring keys") {
    red.set("mykey", "my value")

    val readValue1 = red.get("mykey")
    assert(readValue1 === Some("my value"))

    red.expire("mykey", 1)
    Thread.sleep(2000)

    val readValue2 = red.get("mykey")
    assert(readValue2 === None)
  }

  test("Can set expiring keys at the same time I store") {
    red.setex(key =  "mykey", expiry = 2, value = "my value")

    val readValue1 = red.get("mykey")
    assert(readValue1 === Some("my value"))

    Thread.sleep(2000)

    val readValue2 = red.get("mykey")
    assert(readValue2 === None)
  }


  override def beforeAll() {
    red.flushall
    red.flushdb
  }

  override def afterEach() {
    red.flushall
    red.flushdb
  }
}
