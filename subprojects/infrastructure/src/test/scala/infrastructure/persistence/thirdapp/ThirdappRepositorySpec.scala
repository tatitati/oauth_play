package infrastructure.test.persistence.thirdapp

import domain.model.thirdapp.Thirdapp
import infrastructure.persistence.Exec
import infrastructure.persistence.thirdapp.{ThirdappPersistentModel, ThirdappRepository, ThirdappSchema}
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

class ThirdappRepositorySpec extends FunSuite with BeforeAndAfterEach with Exec {
  val thirdappSchema = TableQuery[ThirdappSchema]
  implicit val db = Database.forConfig("mydb")

  test("I can insert a new third") {
    ThirdappRepository.save(
      BuildThirdappPersistentModel.anyNoPersisted(
        withName = "my row"
      )
    )

    val rows = exec(thirdappSchema.result)

    assert(rows.size === 1)
    assert(rows.isInstanceOf[Vector[_]])
    assert(rows.head.isInstanceOf[ThirdappPersistentModel])
    assert(rows.head.name === "my row")
    assert(rows.head.surrogateId.isInstanceOf[Some[_]])
  }
  
  test("Read return a third aggregate") {
    ThirdappRepository.save(
      BuildThirdappPersistentModel.anyNoPersisted(withName = "my row")
    )

    val third = ThirdappRepository.read(byname = "my row")

    assert(third.isInstanceOf[Thirdapp])
    assert(third.getProfile.name === "my row")
    assert(third.getSurrogateId().isInstanceOf[Some[_]])
  }

  override def beforeEach() {
    exec(thirdappSchema.schema.dropIfExists)
    exec(thirdappSchema.schema.create)
  }
}
