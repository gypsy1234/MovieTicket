package domain

sealed trait TicketType
case object Standard extends TicketType
case object JuniorAndSeniorHighSchoolStudent extends TicketType

case class TicketPrice(value: Long) extends AnyVal {
  def +(that: TicketPrice): TicketPrice = TicketPrice(this.value + that.value)
}


