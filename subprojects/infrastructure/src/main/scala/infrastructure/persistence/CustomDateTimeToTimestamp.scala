package infrastructure.persistence

import com.github.nscala_time.time.Imports.DateTime
import slick.jdbc.MySQLProfile.api._

object CustomDateTimeToTimestamp {
  implicit def dateTimeMapper = MappedColumnType.base[DateTime, String](
    { datetimeObject => datetimeObject.toString("Y-MM-dd H:mm:s") },
    { dateTimeString => DateTime.parse(dateTimeString) }
  )
}
