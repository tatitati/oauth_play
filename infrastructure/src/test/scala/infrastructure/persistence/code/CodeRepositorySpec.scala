package infrastructure.test.persistence.code

import com.redis.RedisClient
import com.typesafe.config.ConfigFactory
import domain.model.code.Code
import infrastructure.persistence.code.CodeRepository
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import test.domain.model.code.BuildCode

class CodeRepositorySpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll {

  val redisClient = new RedisClient(
    ConfigFactory.load().getString("redis.ip"),
    ConfigFactory.load().getString("redis.port").toInt
  )
  val repo = new CodeRepository(redisClient)

  test("Can persist code in redis") {
    val givenCode = BuildCode.any()

    assert(repo.save(givenCode) === true)
  }

  test("Return None when reading an unexisting code") {
    val result = repo.read("anycode_expired")

    assert(result === None)
  }

  test("Return Some when reading an existing code") {
    val givenCode = BuildCode.any()

    repo.save(givenCode, 1)
    val codeFeched = repo.read(givenCode.codeId.toString())

    assert(codeFeched.isInstanceOf[Some[Code]])
    assert(codeFeched.get.equals(givenCode))
  }

  override def beforeAll() {
    redisClient.flushall
    redisClient.flushdb
  }

  override def afterEach() {
    redisClient.flushall
    redisClient.flushdb
  }
}
