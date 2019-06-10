package infrastructure.test.persistence

import infrastructure.persistence.user.UserRepository
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.user.{BuildUser, BuildUserCredentials, BuildUserProfile}
import infrastructure.persistence.user.UserSchema
import test.domain.builders.BuildDate
import com.github.nscala_time.time.Imports.DateTime

class LearningOnUpdateSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {

  val userSchema = TableQuery[UserSchema]

  test("Understand how to update one field in Slick") {
    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(
          withFirstname = "francisco",
          withEmail = "anyemail"
        )
      )
    )

    val query = for {
      userSchema <- userSchema
      if userSchema.firstname === "francisco"
    } yield userSchema.firstname

    assert(query.isInstanceOf[Rep[_]])

    val updateAction = query.update("paco")
    assert(query.updateStatement === "update `user` set `firstname` = ? where `user`.`firstname` = 'francisco'")
    exec(updateAction)
  }

  test("I can update a datetime field") {
    import infrastructure.persistence.CustomDateTimeToTimestamp._

    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(
          withFirstname = "francisco",
          withDatebirth = BuildDate.specificMoment(),
          withEmail = "anyemail"
        )
      )
    )

    val query = for {
      userSchema <- userSchema
      if userSchema.firstname === "francisco"
    } yield userSchema.datebirth

    val newDatebirth =  DateTime.now.withYear(2444)
    val updateAction = query.update(newDatebirth)

    assert(query.updateStatement === "update `user` set `datebirth` = ? where `user`.`firstname` = 'francisco'")
  }

  test("I know how to update multiple fields in slick") {
    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(
          withFirstname = "francisco",
          withEmail = "anyemail"
        ),
      )
    )

    val q = userSchema
      .filter(_.firstname === "francisco")
      .map(
        user => (
          user.firstname,
          user.surname
        ))

    val updateAction = q.update("paco", "jimenez")
    assert(q.updateStatement === "update `user` set `firstname` = ?, `surname` = ? where `user`.`firstname` = 'francisco'")
  }

  test("I know how to update any (multiple or not) fields in slick") {
    import infrastructure.persistence.CustomDateTimeToTimestamp._

    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(
          withFirstname = "francisco",
          withEmail = "anyemail"
        ),
      )
    )

    val persistedUser = exec(userSchema.result).head

    val q = userSchema
      .filter(_.id === persistedUser.id)
      .map(
        user => (
          user.firstname,
          user.surname,
          user.datebirth,
          user.registeredDateTime,
          user.emailconfirmed,
          user.email,
          user.hashPassword
        )
      )

    val updateAction = q.update(
      persistedUser.firstname,
      "Gonzalez",
      persistedUser.datebirth,
      persistedUser.registeredDateTime,
      persistedUser.isEmailConfirmed,
      persistedUser.email,
      persistedUser.hashPassword
    )

    assert(q.updateStatement === "update `user` set `firstname` = ?, `surname` = ?, `datebirth` = ?, `registered_datetime` = ?, `email_confirmed` = ?, `email` = ?, `hashpassword` = ? where `user`.`id` = 1")
  }

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}