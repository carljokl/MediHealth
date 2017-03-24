package com.medidata.model

import java.util.Date
import scala.math.{min,round}


/**
  * User of a medical service
  *
  * @author Carl Jokl
  * @since 21.03.17.
  */
case class User(name: String, dob: Date) {

  def age: Int = 0
}

abstract class Discount(discountPercent: Int) {

  val discount: Int = min(discountPercent, 100)

  def applyDiscount(service: ServiceComponent): Int = {
    round(service.costInPence * ((100 - discount) / 100.0f))
  }

  def applicable(user: User): Boolean
}

object NonDiscount extends Discount(0) {

  override def applyDiscount(service: ServiceComponent): Int = service.costInPence

  def applicable(user: User): Boolean = true
}

case class AgeRangeDiscount(discountPercent: Int, lowerAgeLimit: Int, upperAgeLimit: Int) extends Discount(discountPercent) {

  def applicable(user: User): Boolean = {
    val age = user.age
    lowerAgeLimit <= age && age < upperAgeLimit
  }
}

case class AgeThresholdDiscount(discountPercent: Int, lowerAgeLimit: Int) extends Discount(discountPercent) {

  def applicable(user: User): Boolean = {
    val age = user.age
    lowerAgeLimit <= age
  }
}

case class AgeLimitDiscount(discountPercent: Int, upperAgeLimit: Int) extends Discount(discountPercent) {

  def applicable(user: User): Boolean = {
    val age = user.age
    age <= upperAgeLimit
  }
}

object Discounts {

  val Children: Discount = AgeLimitDiscount(40, 4)
  val SeniorCitizens: Discount = AgeRangeDiscount(60, 65, 70)
  val VerySeniorCitizens: Discount = AgeThresholdDiscount(90, 70)
  val Discounts: Seq[Discount] = Seq(Children, SeniorCitizens, VerySeniorCitizens)

  def apply(user: User): Discount = {
    Discounts.find(_.applicable(user)).getOrElse(NonDiscount)
  }
}
