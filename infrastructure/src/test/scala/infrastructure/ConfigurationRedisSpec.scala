package infrastructure

import com.redis.RedisClient
import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.FunSuite

class ConfigurationRedisSpec extends FunSuite {
  test("configuration is ready for this specific project from infrastructure/configuration.conf") {
    val config :Config = ConfigFactory.load()
    val redisPort=config.getString("redis.port")
    val redisHost=config.getString("redis.ip")

    assert(redisPort === "6379")
    assert(redisHost === "service_redis")
  }

  test("Redis can connect") {
    val redisClient = new RedisClient(
      ConfigFactory.load().getString("redis.ip"),
      ConfigFactory.load().getString("redis.port").toInt
    )

    assert(redisClient.ping === Some("PONG"))
  }
}
