package application

import domain.{CinemaCitizen, Fee, JuniorAndSeniorHighSchoolStudent, Order, Standard}
import org.scalatest._

class TicketFeeCalculationServiceTest
  extends FlatSpec
    with Matchers {

  "TicketFeeCalculationService.calc" should "一般のチケットの料金計算ができる" in {
    val order = Order(Standard)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1800L)

    val rateOrder = Order(Standard, isLateShow = true)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1300L)

    val orders = Seq(order, rateOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3100L)
  }

  it should "中・高校生チケットの料金計算ができる" in {
    val order = Order(JuniorAndSeniorHighSchoolStudent)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val rateOrder = Order(JuniorAndSeniorHighSchoolStudent, isLateShow = true)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1000L)

    val orders = Seq(order, rateOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(2000L)
  }

  it should "シネマシティズンチケットの料金計算ができる" in {
    val order = Order(CinemaCitizen)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val rateOrder = Order(CinemaCitizen, isLateShow = true)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1000L)

    val orders = Seq(order, rateOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(2000L)
  }
}