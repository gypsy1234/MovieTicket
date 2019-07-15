package domain

case class Order(ticketType: TicketType, isLateShow: Boolean = false)