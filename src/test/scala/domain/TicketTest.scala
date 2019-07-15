package domain

import domain.{Fee, Order, Standard}
import org.scalatest._

class TicketTest
  extends FlatSpec
    with Matchers {

  "TicketPrice" should "TicketPrice同士の足し上げができる" in {
    TicketPrice(100L) + TicketPrice(1000L) shouldEqual TicketPrice(1100L)
  }
}