package infrastructure.persistence.code

import java.util.UUID

import domain.model.Scope
import domain.model.code.{Code, CodeId}
import domain.model.thirdapp.ThirdappId
import domain.model.user.UserId
import play.api.libs.json.Json

object CodeSerializer {

  def toJson(code: Code): String = {

    val givenMap = Json.obj(
        "id" -> code.codeId.toString,
        "user_id" -> code.userId.toString,
        "site_id" -> code.thirdappId.toString,
        "state" -> code.state,
        "scope" -> Json.obj(
          "firstname" -> code.scope.firstname,
          "surname" -> code.scope.surname,
          "email" -> code.scope.email
        ),
    )

    Json.stringify(givenMap)
  }

  def toDomain(serialized: String): Code = {

    val parsed = Json.parse(serialized)

    val id = (parsed \ "id").as[String]
    val userid = (parsed \ "user_id").as[String]
    val siteId = (parsed \ "site_id").as[String]
    val scope = Scope(
      firstname = (parsed \ "scope" \ "firstname").as[Boolean],
      surname = (parsed \ "scope" \ "surname").as[Boolean],
      email = (parsed \ "scope" \ "email").as[Boolean],
    )

    new Code(
      codeId = CodeId(UUID.fromString(id)),
      userId = UserId(userid),
      thirdappId = ThirdappId(UUID.fromString(siteId)),
      state = (parsed \ "state").as[String],
      scope = scope
    )
  }
}
