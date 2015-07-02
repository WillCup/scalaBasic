package com.will.utils

import java.text.SimpleDateFormat
import java.util.{Date, Calendar}

import scala.collection.mutable.ListBuffer

/**
 * Created by will on 2015/5/26.
 */
object DateUtil {

  /**
   * get a serious of date, from cal.getTime to yesterday
   * @param cal
   * @param today
   * @param deadLine
   * @return
   */
  def getDateList(cal : Calendar, today : Date, deadLine : Int): List[Date] = {
    var stop = false
    val list = new ListBuffer[Date]
    list.append(cal.getTime)
    for (i <- 1 to (deadLine - 1); if !stop) {
      if ((cal.getTime.getDate + 1) == today.getDate) {
        stop = true
      } else {
        cal.add(Calendar.DATE, 1)
        list.append(cal.getTime)
      }
    }
    list.toList
  }

  /**
   * Get the whole month dates of current month
   * @param cal
   * @return
   */
  def getCurrentMonth(cal : Calendar) = {
    val today = cal.getTime
    val month_day = cal.get(Calendar.DAY_OF_MONTH)
    cal.add(Calendar.DATE, 1 - month_day)
    val list: List[Date] = getDateList(cal, today, month_day)
    list
  }

  /**
   * The difference value between Monday and today
   * @param cal
   * @return
   */
  def getMondayPlus(cal : Calendar): Int = {
    val week_day = cal.get(Calendar.DAY_OF_WEEK)
    var monday_plus = -(week_day - 2)
    if (monday_plus >= 0) {
      monday_plus = monday_plus - 7
    }
    monday_plus
  }

  /**
   * Get the latest week days before yesterday
   * @param cal
   * @return
   */
  def getLastWeek(cal : Calendar = Calendar.getInstance) = {
    val today = cal.getTime
    cal.add(Calendar.DATE, getMondayPlus(cal))
    val list: List[Date] = getDateList(cal, today, 6)
    list
  }

  /**
   * Get the lastest Monday before yesterday
   * @param cal
   * @return
   */
  def getLastMonday(cal : Calendar = Calendar.getInstance()) = {
    val today = cal.getTime
    cal.add(Calendar.DATE, getMondayPlus(cal))
    cal.getTime
  }

  /**
   * Get the first day of current month
   * @param cal
   * @return
   */
  def getMonthFirstDate(cal : Calendar) = {
    val today = cal.getTime
    val month_day = cal.get(Calendar.DAY_OF_MONTH)
    cal.add(Calendar.DATE, 1 - month_day)
    cal.getTime
  }

  def getYesterday() = {
    val cal = Calendar.getInstance()
    cal.add(Calendar.DATE, -1)
    cal.getTime
  }

  /**
   * Get the target day's yesterday string
   * @param date
   * @return
   */
  def getYesterday(date : String) = {
    val format = new SimpleDateFormat("yyyy-MM-dd")
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(format.parse(date))
    cal.add(Calendar.DATE, -1)
    format.format(cal.getTime)
  }


  /**
   * Get last num days for date...
   * @param date
   * @param num
   * @return
   */
  def getLastMultiDays(date : String, num : Int) = {
    val format = new SimpleDateFormat("yyyy-MM-dd")
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(format.parse(date))
    for(i <- 1 to num) yield {
      cal.add(Calendar.DATE, -1)
      format.format(cal.getTime)
    }
  }

  /**
   * Get last num days for date...
   * @param date
   * @param num
   * @return
   */
  def getLastMultiDays(date : Date, num : Int) = {
    val format = new SimpleDateFormat("yyyy-MM-dd")
    val cal: Calendar = Calendar.getInstance()
    cal.setTime(date)
    for(i <- 1 to num) yield {
      cal.add(Calendar.DATE, -1)
      format.format(cal.getTime)
    }
  }

  def getFormator = {
    new SimpleDateFormat("yyyy-MM-dd")
  }
}
