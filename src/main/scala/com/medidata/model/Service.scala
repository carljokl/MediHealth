package com.medidata.model

/**
  * Medical health service
  *
  * @author Carl Jokl
  * @since 21.03.17.
  */
trait ServiceComponent {

  def name: String

  def costInPence: Int

  def practitioner: Practitioner
}

case class SimpleService(name: String, costInPence: Int, practitioner: Practitioner) extends ServiceComponent

case class QuantifiedService(service: ServiceComponent, quantity: Int) extends ServiceComponent {

  def name = service.name

  def costInPence = service.costInPence * quantity

  override def practitioner = service.practitioner
}

case class CompositeService(name: String, baseCostInPence: Int, subComponents: Seq[ServiceComponent], practitioner: Practitioner) extends ServiceComponent {

  def costInPence: Int = {
    baseCostInPence + subComponents.map(_.costInPence).sum
  }
}

case class Practitioner(name: String)

object Practitioners {

  val MediHealth = Practitioner("MediHealth")
  val Bupa = Practitioner("Bupa")
  val NHS = Practitioner("NHS")
}

object Services {

  def diagnosis(practitioner: Practitioner): ServiceComponent = SimpleService("Diagnosis", 6000, practitioner)

  def xray(practitioner: Practitioner): ServiceComponent = SimpleService("X-Ray", 15000, practitioner)

  def bloodTest(practitioner: Practitioner): ServiceComponent = SimpleService("Blood Test", 7800, practitioner)

  def ecg (practitioner: Practitioner): ServiceComponent = SimpleService("ECG", 20040, practitioner)

  def vaccine(practitioner: Practitioner): ServiceComponent = SimpleService("Individual Vaccine", 1500, practitioner)

  def vaccination(practitioner: Practitioner, noOfVaccines: Int): ServiceComponent = {
    CompositeService("Vaccination", 2750, Seq(QuantifiedService(vaccine(practitioner), noOfVaccines)), practitioner)
  }
}

