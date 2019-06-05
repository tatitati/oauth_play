package infrastructure.test.persistence.third

import domain.model.third.Third
import infrastructure.test.persistence.Exec
import infrastructure.persistence.third.{ThirdPersistentModel, ThirdRepository, ThirdSchema}
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, FunSuite}
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import domain.model.third.Third
import test.domain.model.third.{BuildThird, BuildThirdId}

class ThirdRepositorySpec extends FunSuite with BeforeAndAfterEach with BeforeAndAfterAll with Exec {
  val thirdSchema = TableQuery[ThirdSchema]

  test("I can insert a new third") {
    ThirdRepository.save(
      BuildThird.any()
    )

    val rows = exec(thirdSchema.result)

    assert(rows.size === 1)
    assert(rows.isInstanceOf[Vector[_]])
    assert(rows.head.isInstanceOf[ThirdPersistentModel])
  }

  test("I understand how to filter") {
    val query = thirdSchema.filter(_.name === "something").result.statements.mkString
    assert(query === "select `id`, `thirdid`, `name`, `description` from `third` where `name` = 'something'")
  }

  test("Read return a Some[third] aggregate on reading an existing Third") {
    val thirdId = BuildThirdId.any()
    ThirdRepository.save(
      BuildThird.any(withId = thirdId)
    )

    val third = ThirdRepository.findById(thirdId = thirdId)

    assert(third.isInstanceOf[Some[Third]])
  }

  test("Read return a Some[third] aggregate on reading a no existing Third") {
    val thirdId = BuildThirdId.any()

    val third = ThirdRepository.findById(thirdId = thirdId)

    assert(third === None)
  }

  override def beforeEach() {
    exec(thirdSchema.schema.dropIfExists)
    exec(thirdSchema.schema.create)
  }

  override def afterAll() = {
    dbConnection.close()
  }
}
