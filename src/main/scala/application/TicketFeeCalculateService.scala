package application

import domain.{CinemaCitizen, CinemaCitizenSenior, CompanionOfHandicappedPeople, Fee, HandicappedPeople, JuniorAndSeniorHighSchoolStudent, Order, ScreeningDatetime, Senior, Standard, TicketPrice, TicketType, ToddlerAndElementarySchoolStudent, UniversityAndVocationalCollegeStudent}

case object TicketFeeCalculateService {

  def calc(order: Order): Fee = calc(Seq(order))

  def calc(orders: Seq[Order]): Fee = {
    validate(orders)
    val totalPrice = orders.foldLeft(TicketPrice(0L)) {(acc, v) => acc + price(v)}
    Fee(totalPrice.value)
  }

  private def validate(orders: Seq[Order]) = {
    // とりあえずrequireで
    require(orders.count(_.ticketType == CompanionOfHandicappedPeople) <= orders.count(_.ticketType == HandicappedPeople))
  }

  private def price(order: Order) =
    order match {
      case Order(Standard, sd: ScreeningDatetime) if isMovieDay(sd) => TicketPrice(1100L)
      case Order(Standard, sd: ScreeningDatetime) if isLateTime(sd) => TicketPrice(1300L)
      case Order(Standard, _) => TicketPrice(1800L)
      case Order(Senior, _) => TicketPrice(1100L)
      case Order(UniversityAndVocationalCollegeStudent, sd: ScreeningDatetime) if isMovieDay(sd) => TicketPrice(1100L)
      case Order(UniversityAndVocationalCollegeStudent, sd: ScreeningDatetime) if isLateTime(sd) => TicketPrice(1300L)
      case Order(UniversityAndVocationalCollegeStudent, _) => TicketPrice(1500L)
      case Order(JuniorAndSeniorHighSchoolStudent, _) => TicketPrice(1000L)
      case Order(ToddlerAndElementarySchoolStudent, _) => TicketPrice(1000L)
      case Order(HandicappedPeople, _) => TicketPrice(1000L)
      case Order(CompanionOfHandicappedPeople, _) => TicketPrice(1000L)
      case Order(CinemaCitizen, sd: ScreeningDatetime) if isMovieDay(sd) => TicketPrice(1100L)
      case Order(CinemaCitizen, sd: ScreeningDatetime) if sd.isHoliday && !isLateTime(sd) => TicketPrice(1300L)
      case Order(CinemaCitizen, _) => TicketPrice(1000L)
      case Order(CinemaCitizenSenior, _) => TicketPrice(1000L)
    }

  private def isLateTime(time: ScreeningDatetime) =
    if (time.hour < 20) false
    else true

  private def isMovieDay(datetime: ScreeningDatetime): Boolean = datetime.dayOfMonth == 1
}
