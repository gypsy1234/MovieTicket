package application

import domain.{CinemaCitizen, Fee, JuniorAndSeniorHighSchoolStudent, Order, ScreeningDatetime, Standard, TicketPrice, TicketType}

case object TicketFeeCalculateService {

  def calc(order: Order): Fee = calc(Seq(order))

  def calc(orders: Seq[Order]): Fee = {
    val totalPrice = orders.foldLeft(TicketPrice(0L)) {(acc, v) => acc + price(v)}
    Fee(totalPrice.value)
  }

  private def price(order: Order) =
    order match {
      case Order(Standard, sd: ScreeningDatetime) if isMovieDay(sd) => TicketPrice(1100L)
      case Order(Standard, sd: ScreeningDatetime) if isLateTime(sd) => TicketPrice(1300L)
      case Order(Standard, _) => TicketPrice(1800L)
      case Order(JuniorAndSeniorHighSchoolStudent, _) => TicketPrice(1000L)
      case Order(CinemaCitizen, sd: ScreeningDatetime) if isMovieDay(sd) => TicketPrice(1100L)
      case Order(CinemaCitizen, sd: ScreeningDatetime) if sd.isHoliday && !isLateTime(sd) => TicketPrice(1300L)
      case Order(CinemaCitizen, _) => TicketPrice(1000L)
    }

  private def isLateTime(time: ScreeningDatetime) =
    if (time.hour < 20) false
    else true

  private def isMovieDay(datetime: ScreeningDatetime): Boolean = datetime.dayOfMonth == 1
}
