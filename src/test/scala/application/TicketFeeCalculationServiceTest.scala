package application

import java.time.LocalDateTime

import domain.{CinemaCitizen, Fee, JuniorAndSeniorHighSchoolStudent, Order, ScreeningDatetime, Standard}
import org.scalatest._

class TicketFeeCalculationServiceTest
  extends FlatSpec
    with Matchers {

  trait Fixture {
    val DayTime = ScreeningDatetime(LocalDateTime.of(2019, 7, 12, 19, 59))
    val LateTime = ScreeningDatetime(LocalDateTime.of(2019, 7, 12, 20, 0))
    val HolidayDayTime = ScreeningDatetime(LocalDateTime.of(2019, 7, 15, 19, 59))
    val HolidayLateTime = ScreeningDatetime(LocalDateTime.of(2019, 7, 15, 20, 0))
  }

  "TicketFeeCalculationService.calc" should "一般のチケットの料金計算ができる" in new Fixture {
    val order = Order(Standard, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1800L)

    val rateOrder = Order(Standard, screeningDatetime = LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1300L)

    val orders = Seq(order, rateOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3100L)
  }

  it should "中・高校生チケットの料金計算ができる" in new Fixture {
    val order = Order(JuniorAndSeniorHighSchoolStudent, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val rateOrder = Order(JuniorAndSeniorHighSchoolStudent, LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1000L)

    val orders = Seq(order, rateOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(2000L)
  }

  it should "シネマシティズンチケットの料金計算ができる" in new Fixture {
    val order = Order(CinemaCitizen, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val rateOrder = Order(CinemaCitizen, LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1000L)

    val holidayDayOrder = Order(CinemaCitizen, HolidayDayTime)
    TicketFeeCalculateService.calc(holidayDayOrder) shouldEqual Fee(1300L)

    val holidayLateOrder = Order(CinemaCitizen, HolidayLateTime)
    TicketFeeCalculateService.calc(holidayLateOrder) shouldEqual Fee(1000L)

    val orders = Seq(order, rateOrder, holidayDayOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3300L)
  }
}