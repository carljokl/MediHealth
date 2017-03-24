package com.medidata.model

import org.scalatest._

/**
  * Specification for tests relating to users
  *
  * @author Carl Jokl
  * @since 22.03.17.
  */
class UserSpec extends FlatSpec with Matchers {

   val testChildUser = User("Test Child", DobUtils.ageToDob(4))

   "A user" should "correctly calculate the age based on a date of birth!" in {
     testChildUser.age should be (4)
   }
}
