package test.app.learning.Redis

import org.scalatest.FunSuite
import com.redis._

class ConnectSpec extends FunSuite {

  test("redis-server is up") {
    val redisClient = new RedisClient(
      sys.env.get("CONTAINER_REDIS_PORT_6379_TCP_ADDR").get,
      6379
    )
    val response = redisClient.ping

    assert(response === Some("PONG"))
  }
}
