package application

import domain.{CinemaCitizen, Fee, JuniorAndSeniorHighSchoolStudent, Order, Standard}
import org.scalatest._

class TicketFeeCalculationServiceTest
  extends FlatSpec
    with Matchers {

    "TicketFeeCalculationService.calc" should "一般のチケットの料金計算ができる" in {
      val order = Order(Standard)
      TicketFeeCalculateService.calc(order) shouldEqual Fee(1800L)

      val orders = Seq(Order(Standard), Order(Standard))
      TicketFeeCalculateService.calc(orders) shouldEqual Fee(3600L)
    }

  it should "一般のレイトショーに対応している" in {
    val order = Order(Standard, isLateShow = true)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1300L)

    val orders = Seq(Order(Standard, isLateShow = true), Order(Standard))
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3100L)
  }

  it should "中・高校生チケットの料金計算ができる" in {
    val order = Order(JuniorAndSeniorHighSchoolStudent)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1100L)

    val orders = Seq(Order(JuniorAndSeniorHighSchoolStudent), Order(JuniorAndSeniorHighSchoolStudent))
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(2200L)
  }

  it should "シネマシティズンチケットの料金計算ができる" in {
    val order = Order(CinemaCitizen)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val orders = Seq(Order(CinemaCitizen), Order(CinemaCitizen))
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(2000L)
  }
}