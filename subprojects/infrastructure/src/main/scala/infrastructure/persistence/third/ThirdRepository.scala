package infrastructure.persistence.third

import domain.model.third.Third
import infrastructure.persistence.Exec
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

object ThirdRepository extends Exec {

  val thirdSchema = TableQuery[ThirdSchema]
  implicit val db = Database.forConfig("mydb")

  def save(persistentModel: ThirdPersistentModel): Unit = {
    exec(thirdSchema += persistentModel)
  }

  def read(byname: String): Third = {
    val rows = exec(thirdSchema.filter(_.name === byname).result)

    val thirdPersisted = rows.head

    ThirdMapper.toDomain(thirdPersisted)
  }
}
