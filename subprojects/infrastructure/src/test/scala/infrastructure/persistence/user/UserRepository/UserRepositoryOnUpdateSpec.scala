package infrastructure.test.persistence.user.UserRepository

import infrastructure.persistence.user.UserRepository
import infrastructure.test.persistence.Exec
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.user.{BuildUser, BuildUserCredentials, BuildUserProfile}
import infrastructure.persistence.user.UserSchema

class UserRepositoryOnUpdateSpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val userSchema = TableQuery[UserSchema]

  test("Understand how to update one field in Slick") {
    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(withFirstname = "francisco"),
        withUserCredentials = BuildUserCredentials.any(withEmail = "anyemail")
      )
    )

    val q = for {
      c <- userSchema
      if c.firstname === "francisco"
    } yield c.firstname

    assert(q.isInstanceOf[Rep[_]])

    val updateAction = q.update("paco")
    assert(q.updateStatement === "update `user` set `firstname` = ? where `user`.`firstname` = 'francisco'")
    exec(updateAction)
  }

  test("I know how to update multiple fields in slick") {
    UserRepository.save(
      BuildUser.anyNoPersisted(
        withProfile = BuildUserProfile.any(withFirstname = "francisco"),
        withUserCredentials = BuildUserCredentials.any(withEmail = "anyemail")
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
    assert(q.updateStatement === "update `user` set `firstname` = ? where `user`.`firstname` = 'francisco'")
    exec(updateAction)
  }

  override def beforeEach() {
    exec(userSchema.schema.dropIfExists)
    exec(userSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
