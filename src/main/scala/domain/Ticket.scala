package domain

sealed trait TicketType
case object CinemaCitizen extends TicketType
case object CinemaCitizenSenior extends TicketType
case object Standard extends TicketType
case object Senior extends TicketType
case object UniversityAndVocationalCollegeStudent extends TicketType
case object JuniorAndSeniorHighSchoolStudent extends TicketType
case object ToddlerAndElementarySchoolStudent extends TicketType
case object HandicappedPeople extends TicketType
case object CompanionOfHandicappedPeople extends TicketType

case class TicketPrice(value: Long) extends AnyVal {
  def +(that: TicketPrice): TicketPrice = TicketPrice(this.value + that.value)
}


