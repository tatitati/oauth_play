package infrastructure.persistence.code

import com.redis.RedisClient
import domain.model.code.Code

class CodeRepository(val redisClient: RedisClient) {

  def save(code: Code, expiryTime: Int = 60): Boolean = {

    redisClient.setex(
      key = code.codeId.toString,
      expiry = expiryTime,
      value = CodeSerializer.toJson(code)
    )
  }

  def read(code: String): Option[Code] = {
    redisClient.get(code) match {
      case Some(value) => Some(CodeSerializer.toDomain(value))
      case None => None
    }
  }
}
