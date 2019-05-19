package infrastructure.persistence.third

case class ThirdPersistentModel(
   surrogateId: Option[Long] = None,
   id: String,
   name: String,
   description: String
)
