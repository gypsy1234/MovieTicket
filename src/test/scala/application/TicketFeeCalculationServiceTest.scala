package application

import java.time.LocalDateTime

import domain.{CinemaCitizen, CinemaCitizenSenior, CompanionOfHandicappedPeople, Fee, HandicappedPeople, JuniorAndSeniorHighSchoolStudent, Order, ScreeningDatetime, Senior, Standard, ToddlerAndElementarySchoolStudent, UniversityAndVocationalCollegeStudent}
import org.scalatest._

class TicketFeeCalculationServiceTest
  extends FlatSpec
    with Matchers {

  trait Fixture {
    val DayTime = ScreeningDatetime(LocalDateTime.of(2019, 7, 12, 19, 59))
    val LateTime = ScreeningDatetime(LocalDateTime.of(2019, 7, 12, 20, 0))
    val HolidayDayTime = ScreeningDatetime(LocalDateTime.of(2019, 7, 15, 19, 59))
    val HolidayLateTime = ScreeningDatetime(LocalDateTime.of(2019, 7, 15, 20, 0))
    val MovieDay = ScreeningDatetime(LocalDateTime.of(2019, 7, 1, 20, 0))
  }

  "TicketFeeCalculationService.calc" should "一般のチケットの料金計算ができる" in new Fixture {
    val order = Order(Standard, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1800L)

    val rateOrder = Order(Standard, screeningDatetime = LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1300L)

    val movieDayOrder = Order(Standard, screeningDatetime = MovieDay)
    TicketFeeCalculateService.calc(movieDayOrder) shouldEqual Fee(1100L)

    val orders = Seq(order, rateOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3100L)
  }

  it should "中・高校生チケットの料金計算ができる" in new Fixture {
    val order = Order(JuniorAndSeniorHighSchoolStudent, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val rateOrder = Order(JuniorAndSeniorHighSchoolStudent, LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1000L)

    val movieDayOrder = Order(JuniorAndSeniorHighSchoolStudent, screeningDatetime = MovieDay)
    TicketFeeCalculateService.calc(movieDayOrder) shouldEqual Fee(1000L)

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

    val movieDayOrder = Order(CinemaCitizen, MovieDay)
    TicketFeeCalculateService.calc(movieDayOrder) shouldEqual Fee(1100L)

    val orders = Seq(order, rateOrder, holidayDayOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3300L)
  }

  it should "シネマシティズン（60才以上）チケットの料金計算ができる" in new Fixture {
    val order = Order(CinemaCitizenSenior, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val rateOrder = Order(CinemaCitizenSenior, LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1000L)

    val holidayDayOrder = Order(CinemaCitizenSenior, HolidayDayTime)
    TicketFeeCalculateService.calc(holidayDayOrder) shouldEqual Fee(1000L)

    val holidayLateOrder = Order(CinemaCitizenSenior, HolidayLateTime)
    TicketFeeCalculateService.calc(holidayLateOrder) shouldEqual Fee(1000L)

    val movieDayOrder = Order(CinemaCitizenSenior, MovieDay)
    TicketFeeCalculateService.calc(movieDayOrder) shouldEqual Fee(1000L)

    val orders = Seq(order, rateOrder, holidayDayOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3000L)
  }

  it should "シニア（70才以上）チケットの料金計算ができる" in new Fixture {
    val order = Order(Senior, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1100L)

    val rateOrder = Order(Senior, LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1100L)

    val holidayDayOrder = Order(Senior, HolidayDayTime)
    TicketFeeCalculateService.calc(holidayDayOrder) shouldEqual Fee(1100L)

    val holidayLateOrder = Order(Senior, HolidayLateTime)
    TicketFeeCalculateService.calc(holidayLateOrder) shouldEqual Fee(1100L)

    val movieDayOrder = Order(Senior, MovieDay)
    TicketFeeCalculateService.calc(movieDayOrder) shouldEqual Fee(1100L)

    val orders = Seq(order, rateOrder, holidayDayOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3300L)
  }

  it should "学生（大・専）チケットの料金計算ができる" in new Fixture {
    val order = Order(UniversityAndVocationalCollegeStudent, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1500L)

    val rateOrder = Order(UniversityAndVocationalCollegeStudent, LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1300L)

    val holidayDayOrder = Order(UniversityAndVocationalCollegeStudent, HolidayDayTime)
    TicketFeeCalculateService.calc(holidayDayOrder) shouldEqual Fee(1500L)

    val holidayLateOrder = Order(UniversityAndVocationalCollegeStudent, HolidayLateTime)
    TicketFeeCalculateService.calc(holidayLateOrder) shouldEqual Fee(1300L)

    val movieDayOrder = Order(UniversityAndVocationalCollegeStudent, MovieDay)
    TicketFeeCalculateService.calc(movieDayOrder) shouldEqual Fee(1100L)

    val orders = Seq(order, rateOrder, holidayDayOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(4300L)
  }

  it should "幼児（3才以上）・小学生チケットの料金計算ができる" in new Fixture {
    val order = Order(ToddlerAndElementarySchoolStudent, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val rateOrder = Order(ToddlerAndElementarySchoolStudent, LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1000L)

    val holidayDayOrder = Order(ToddlerAndElementarySchoolStudent, HolidayDayTime)
    TicketFeeCalculateService.calc(holidayDayOrder) shouldEqual Fee(1000L)

    val holidayLateOrder = Order(ToddlerAndElementarySchoolStudent, HolidayLateTime)
    TicketFeeCalculateService.calc(holidayLateOrder) shouldEqual Fee(1000L)

    val movieDayOrder = Order(ToddlerAndElementarySchoolStudent, MovieDay)
    TicketFeeCalculateService.calc(movieDayOrder) shouldEqual Fee(1000L)

    val orders = Seq(order, rateOrder, holidayDayOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3000L)
  }

  it should "障がい者（学生以上）チケットの料金計算ができる" in new Fixture {
    val order = Order(HandicappedPeople, DayTime)
    TicketFeeCalculateService.calc(order) shouldEqual Fee(1000L)

    val rateOrder = Order(HandicappedPeople, LateTime)
    TicketFeeCalculateService.calc(rateOrder) shouldEqual Fee(1000L)

    val holidayDayOrder = Order(HandicappedPeople, HolidayDayTime)
    TicketFeeCalculateService.calc(holidayDayOrder) shouldEqual Fee(1000L)

    val holidayLateOrder = Order(HandicappedPeople, HolidayLateTime)
    TicketFeeCalculateService.calc(holidayLateOrder) shouldEqual Fee(1000L)

    val movieDayOrder = Order(HandicappedPeople, MovieDay)
    TicketFeeCalculateService.calc(movieDayOrder) shouldEqual Fee(1000L)

    val withCompanion = Seq(Order(HandicappedPeople, DayTime), Order(CompanionOfHandicappedPeople, DayTime))
    TicketFeeCalculateService.calc(withCompanion) shouldEqual Fee(2000L)

    val orders = Seq(order, rateOrder, holidayDayOrder)
    TicketFeeCalculateService.calc(orders) shouldEqual Fee(3000L)
  }
}