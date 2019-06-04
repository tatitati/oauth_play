package infrastructure.persistence

import com.github.nscala_time.time.Imports.{DateTime, DateTimeFormat}
import slick.jdbc.MySQLProfile.api._
import com.github.nscala_time.time.Imports._


object CustomDateTimeToTimestamp {
  implicit def dateTimeMapper = MappedColumnType.base[DateTime, String](
    { datetimeObject => datetimeObject.toString("Y-MM-dd H:mm:s") },
    { dateTimeString => DateTimeFormat.forPattern("Y-MM-dd H:mm:s.S").parseDateTime(dateTimeString) }
  )
}
