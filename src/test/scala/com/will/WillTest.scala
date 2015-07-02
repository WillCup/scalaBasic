package com.will

/**
 * Created by will on 2015/5/25.
 */


import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import com.eunke.util.DFUitls._
import com.eunke.util.JsonUtil._
import com.eunke.util.StringUtil._
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}
import org.json4s.JsonAST.{JNothing, JValue}
import org.json4s.jackson.JsonMethods._
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.mutable.ListBuffer

object WillTest {

  def locAnalyze: Unit = {
    //    val str = "{\"loc\":[{\"address\":\"江苏省苏州市相城区Y506(渭泾塘路)\",\"latitude\":31.458476258121042,\"longitude\":120.67761659704519,\"time\":1432137600}]}"
    val str = "{\"ip\":\"36.250.88.73\",\"auth\":\"{\\\"driverId\\\":2008273,\\\"key\\\":\\\"539f2a6f-9382-4263-b03b-f0483aeb7059\\\"}\",\"accessClass\":\"com.eunke.donkey.web.controller.logi.DriverProfileController\",\"accessMethod\":\"sendDriverLoc\",\"deviceInfo\":{\"imei\":\"866231022300739\",\"imsi\":\"460021241609855\",\"phone\":\"\",\"device\":\"2014501\",\"osVersion\":\"4.4.2\",\"clientVersion\":\"2.5.0\",\"channel\":\"m360\",\"width\":720,\"height\":1280,\"g\":\"gnpoljl5btgom8g01yd5t4hyp2d72z1s\",\"platform\":\"android\"},\"request\":\"{\\\"loc\\\":[{\\\"address\\\":\\\"浙江省绍兴市绍兴县钱陶公路\\\",\\\"latitude\\\":30.094768524169922,\\\"longitude\\\":120.53356170654297,\\\"time\\\":1432137598777}]}\",\"response\":\"{\\\"result\\\":{\\\"code\\\":\\\"OK\\\",\\\"errorInfo\\\":\\\"\\\"},\\\"periodSecond\\\":2000}\",\"uid\":\"2008273\",\"date\":\"2015-05-21 00:00:00\"}"
    //    print(extract(parse(str) \ "request"))
    val req = extract(parse(str) \ "request")
    println(req.replace("\\", ""))
    println(extract(parse(req.replace("\\", "")) \ "loc"))
    println("dsfsd -> " + extractTwice(parse(str) \ "request", "loc"))
  }

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
  def getLastMonth(cal : Calendar) = {
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
  def getLastWeek(cal : Calendar) = {
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
  def getLastMonday(cal : Calendar) = {
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

  def main (args: Array[String]) {
//    locAnalyze

    val today = new Date()
    val format = new SimpleDateFormat("yyyy-MM-dd")

    val cal = Calendar.getInstance()
    cal.setTime(today)
    cal.add(Calendar.DATE, -3)

    getLastWeek(cal).foreach(
      d => println(format.format(d))
    )

    getLastMonth(cal).foreach(
      d => println(format.format(d))
    )

    println(format.format(getLastMonday(cal)))
    println(format.format(getMonthFirstDate(cal)))
  }
}
