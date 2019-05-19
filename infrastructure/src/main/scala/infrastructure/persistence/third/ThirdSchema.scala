package infrastructure.persistence.third

import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

class ThirdSchema(tag: Tag) extends Table[ThirdPersistentModel](tag, "third") {
  def surrogateId = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def thirdId = column[String]("thirdid")
  def name = column[String]("name", O.SqlType("VARCHAR(255)"))
  def description = column[String]("description")

  def * = (
    surrogateId,
    thirdId,
    name,
    description
  ).mapTo[ThirdPersistentModel]
}
