package infrastructure.persistence.thirdapp

import slick.jdbc.MySQLProfile.api._
import slick.lifted.Tag

class ThirdappSchema(tag: Tag) extends Table[ThirdappPersistentModel](tag, "thirdapp") {
  def surrogateId = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
  def thirdappId = column[String]("thirdappid")
  def thirdId = column[String]("thirdid")
  def clientid = column[String]("clientid")
  def clientsecret = column[String]("clientsecret")
  def name = column[String]("name")
  def domain = column[String]("domain")
  def description = column[String]("description")
  def homepage = column[String]("homepage")
  def urlcallback = column[String]("urlcallback")

  def * = (
      surrogateId,
      thirdappId,
      thirdId,
      clientid,
      clientsecret,
      name,
      domain,
      description,
      homepage,
      urlcallback
    ).mapTo[ThirdappPersistentModel]
}
