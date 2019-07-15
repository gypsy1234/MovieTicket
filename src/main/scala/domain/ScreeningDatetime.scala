package domain

import java.time.LocalDateTime

case class ScreeningDatetime(private val value: LocalDateTime) {
  val hour: Int = value.getHour
}
