package infrastructure.persistence.thirdapp

import domain.model.thirdapp.Thirdapp
import infrastructure.persistence.Exec
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

object ThirdappRepository extends Exec {

  val thirdappSchema = TableQuery[ThirdappSchema]
  implicit val db = Database.forConfig("mydb")

  def save(persistentModel: ThirdappPersistentModel): Unit = {
    exec(thirdappSchema += persistentModel)
  }

  def read(byname: String): Thirdapp = {
    val rows = exec(thirdappSchema.filter(_.name === byname).result)

    val thirdPersisted = rows.head

    ThirdappMapper.toDomain(thirdPersisted)
  }
}
