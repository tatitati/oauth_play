package infrastructure.persistence.third

import domain.model.third.Third
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery
import scala.concurrent.Await
import scala.concurrent.duration._

object ThirdRepository {

  val thirdSchema = TableQuery[ThirdSchema]
  implicit val db = Database.forConfig("mydb")

  def save(persistentModel: ThirdPersistentModel): Unit = {
    db.run(thirdSchema += persistentModel)
  }

  def read(byname: String): Third = {
    val future = db.run(thirdSchema.filter(_.name === byname).result)
    val rows = Await.result(future, 2.seconds)

    val thirdPersisted = rows.head

    ThirdMapper.toDomain(thirdPersisted)
  }
}
