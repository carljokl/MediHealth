package com.medidata.model

/**
  * Medical health service
  *
  * @author Carl Jokl
  * @since 21.03.17.
  */
abstract class ServiceComponent {

  def name: String

  def costInPence: Int
}

case class SimpleService(name: String, costInPence: Int) extends ServiceComponent

case class QuantifiedComponent(service: ServiceComponent, quantity: Int) extends ServiceComponent {

  def name = service.name

  def costInPence = service.costInPence * quantity
}

case class CompositeService(name: String, baseCostInPence: Int, subComponents: Seq[ServiceComponent]) extends ServiceComponent {

  def costInPence: Int = {
    baseCostInPence + subComponents.map(_.costInPence).sum
  }


}

object Services {

  val Diagnosis = SimpleService("Diagnosis", 6000)
  val XRay = SimpleService("X-Ray", 150000)
  val BloodTest = SimpleService("Blood Test", 7800)
  val ECG = SimpleService("ECG", 20040)
  val Vaccine = SimpleService("Individual Vaccine", 1500)

  def Vaccination(noOfVaccines: Int): Unit = {
    CompositeService("Vaccination", 2750, Seq(QuantifiedComponent(Vaccine, noOfVaccines)))
  }
}

