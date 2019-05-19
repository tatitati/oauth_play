package infrastructure

import com.typesafe.config.{Config, ConfigFactory}
import org.scalatest.FunSuite

class ConfigurationSpec extends FunSuite {
  test("configuration is ready for this specific project from infrastructure/configuration.conf") {
    val config :Config = ConfigFactory.load()
    val redisport=config.getString("redis.port")

    assert(redisport === "6379")
  }
}
