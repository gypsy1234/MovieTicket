package application

import domain.{Fee, JuniorAndSeniorHighSchoolStudent, Order, Standard, TicketPrice, TicketType}

case object TicketFeeCalculateService {

  def calc(order: Order): Fee = calc(Seq(order))

  def calc(orders: Seq[Order]): Fee = {
    val totalPrice = orders.foldLeft(TicketPrice(0L)) {(acc, v) => acc + price(v)}
    Fee(totalPrice.value)
  }

  private def price(order: Order) =
    order match {
      case Order(Standard, false) => TicketPrice(1800L)
      case Order(Standard, true) => TicketPrice(1300L)
      case Order(JuniorAndSeniorHighSchoolStudent, _) => TicketPrice(1100L)
    }
}