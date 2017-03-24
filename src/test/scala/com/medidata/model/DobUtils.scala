package com.medidata.model

import java.util.{Calendar, Date}

/**
  * @author Carl Jokl
  * @since 24.03.17.
  */
object DobUtils {

  def toDob(year: Int, month: Int, day: Int): Date = {
    val calendar: Calendar = Calendar.getInstance()
    calendar.set(year, month, day, 1, 1, 1)
    calendar.getTime
  }

  def ageToDob(age: Int): Date = {
    val calendar: Calendar = Calendar.getInstance()
    calendar.roll(Calendar.YEAR, -1 * age)
    calendar.getTime
  }
}
