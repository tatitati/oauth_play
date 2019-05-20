package test.domain.builders

import com.github.nscala_time.time.Imports.{DateTime, _}
import scala.util.Random

object BuildDate {
  def any(): DateTime = {
    val past = DateTime.now.withYear(2000).withMonth(8).withDay(20)
    val now = DateTime.now
    val future = DateTime.now.withYear(2030).withMonth(8).withDay(20)


    val dates = Vector(past, now, future)
    dates(Random.nextInt(dates.length))
  }

  def inFuture(): DateTime = {
    DateTime.now.withYear(2030).withMonth(8).withDay(20)
  }

  def now(): DateTime = {
    DateTime.now
  }

  def inPast(): DateTime = {
    DateTime.now.withYear(2000).withMonth(8).withDay(20)
  }
}
