package com.medidata.model
import org.scalatest._

/**
  * Specification for tests relating to services
  *
  * @author Carl Jokl
  * @since 22.03.17.
  */
class ServiceSpec extends FlatSpec with Matchers {

   val testSimpleService: ServiceComponent = SimpleService("Test simple service", 7800, Practitioners.MediHealth)
   val testQuantifiedService = QuantifiedService(testSimpleService, 5)
   val testCompositeService = CompositeService("test composite", 1234, Seq(testQuantifiedService), Practitioners.MediHealth)

   "A Simple service" should "have a price that matches the base price with which it was created!" in {
     testSimpleService.costInPence should be (7800)
   }

  "A quantified service" should "have a price that is the quantity multipled by the base price" in {
    testQuantifiedService.costInPence should be (5 * 7800)
  }

  "A composite Service" should "have a price that is equal to the sum of all the services that make it up" in {
    testCompositeService.costInPence should be ((5 * 7800) + 1234)
  }
}
