package infrastructure.persistence.thirdapp

import domain.model.thirdapp.Thirdapp
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import scala.concurrent.duration._
import scala.concurrent.Await

object ThirdappRepository {

  val thirdappSchema = TableQuery[ThirdappSchema]
  implicit val db = Database.forConfig("mydb")

  def save(persistentModel: ThirdappPersistentModel): Unit = {
    db.run(thirdappSchema += persistentModel)
  }

  def read(byname: String): Thirdapp = {
    val future = db.run(thirdappSchema.filter(_.name === byname).result)
    val rows = Await.result(future, 2.seconds)

    val thirdPersisted = rows.head

    ThirdappMapper.toDomain(thirdPersisted)
  }
}
