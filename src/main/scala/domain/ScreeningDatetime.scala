package domain

import java.time.{LocalDate, LocalDateTime}

case class ScreeningDatetime(private val value: LocalDateTime) {
  val hour: Int = value.getHour
  val dayOfMonth: Int = value.getDayOfMonth
  // 祝日等の判定が面倒なので、2019年7月15日のみ土日祝とする
  val isHoliday: Boolean = value.toLocalDate.isEqual(LocalDate.of(2019, 7, 15))
}
