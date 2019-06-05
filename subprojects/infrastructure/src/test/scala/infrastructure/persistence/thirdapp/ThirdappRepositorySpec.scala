package infrastructure.test.persistence.thirdapp

import domain.model.thirdapp.Thirdapp
import infrastructure.test.persistence.Exec
import infrastructure.persistence.thirdapp.{ThirdappPersistentModel, ThirdappRepository, ThirdappSchema}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import test.domain.model.thirdapp.{BuildThirdapp, BuildThirdappCredentials}

class ThirdappRepositorySpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val thirdappSchema = TableQuery[ThirdappSchema]

  test("Can insert a new thirdapp") {
    ThirdappRepository.save(
      BuildThirdapp.any()
    )

    val rows = exec(thirdappSchema.result)

    assert(rows.size === 1)
    assert(rows.isInstanceOf[Vector[_]])
    assert(rows.head.isInstanceOf[ThirdappPersistentModel])
  }
  
  test("Return Some on reading an existing thirdapp ") {
    val credentials = BuildThirdappCredentials.any()

    ThirdappRepository.save(
      BuildThirdapp.any(
        withCredentials = credentials
      )
    )

    val third = ThirdappRepository.read(credentials = credentials)

    assert(third.isInstanceOf[Some[Thirdapp]])
    assert(third.get.getCredentials === credentials)
    assert(third.get.getSurrogateId().isInstanceOf[Some[_]])
  }

  test("Return None on reading an non-existing thirdapp ") {
    val credentials = BuildThirdappCredentials.any()

    val third = ThirdappRepository.read(credentials = credentials)

    assert(third === None)
  }

  test("Return true if a thirdapp exists") {
    val credentials = BuildThirdappCredentials.any()

    ThirdappRepository.save(
      BuildThirdapp.any(
        withCredentials = credentials
      )
    )

    val third = ThirdappRepository.existByCredentials(credentials = credentials)

    assert(third === true)
  }

  test("Returns False if a thirdapp no exists") {
    val credentials = BuildThirdappCredentials.any()

    val third = ThirdappRepository.existByCredentials(credentials = credentials)

    assert(third === false)
  }

  override def beforeEach() {
    exec(thirdappSchema.schema.dropIfExists)
    exec(thirdappSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
