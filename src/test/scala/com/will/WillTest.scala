package com.will

/**
 * Created by will on 2015/5/25.
 */


import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import com.will.utils.DateUtil._
import com.will.utils.JsonUtil._
import org.json4s.jackson.JsonMethods._
import org.junit.Test

import scala.collection.immutable.IndexedSeq

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
  case class Lang(name :String, age : Int)

  def main (args: Array[String]) {
//    locAnalyze
//    dateTest

    val str = "冀\t河北省\t张家口市\t冀G\t\t\t"
    println(str.split("\t").length)
    object WeekDay extends Enumeration {
      type WeekDay = Value
      val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value
    }

      import WeekDay._

      println(WeekDay.Fri)
      def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)
      WeekDay.values filter isWorkingDay foreach println

//    val str = "(2049857, 2.4.0, , -101, Y_00TD_0008, 郑州)"
//    str.substring(1, str.length - 1).split(",").foreach(println)
//    println(str.substring(1, str.length - 1).split(",").length)
  }

  def getDate(date :String) = {
    val format = new SimpleDateFormat("yyyy-MM-dd")
    format.parse(date)
  }

  def dateTest = {
    val today = new Date()
    val format = new SimpleDateFormat("yyyy-MM-dd")

    val cal = Calendar.getInstance()
    cal.setTime(today)
    cal.add(Calendar.DATE, 1)
    val this_week_plus = getMondayPlus(cal)
    cal.add(Calendar.DATE, this_week_plus)
    println("Monday is : " + format.format(cal.getTime))
    cal.add(Calendar.MONTH, -1)

    getCurrentMonth(cal).foreach(l => println(format.format(l)))
//    cal.setTime(today)
//    cal.add(Calendar.DATE, -3)
//
//    getLastWeek(cal).foreach(
//      d => println(format.format(d))
//    )
//
//    getLastMonth(cal).foreach(
//      d => println(format.format(d))
//    )
//
//    println("latest Mongday is " + format.format(getLastMonday()))
//    println(format.format(getMonthFirstDate(cal)))
//    println("yesterday is : " + format.format(getYesterday()))
//    println("yesterday for 2015-05-21 is : " + getYesterday("2015-05-21"))
//    println("last 5 days for 2015-05-21 is : " + getLastMultiDays("2015-05-21", 5))
//    val days: IndexedSeq[String] = getLastMultiDays(new Date(), 4)
//    days.foreach(println)
//    print(days.foldLeft(0)((a, b) =>   a + b.length ))

  }
}
