package com.will.utils

import scala.collection.mutable.ListBuffer

/**
 * Created by will on 5/22/15.
 */
object StringUtil {


  def merge(version: String): String = {
    version.substring(0, 2) + "X"
  }

  /**
   * analyze a string separated by seperator, return a list
   * @param str
   * @return
   */
  def str2List(seperator: String)(str: String): List[String] = {
    val tmp = new ListBuffer[String]()
    for (s <- str.split(seperator)) {
      tmp.append(s)
    }
    tmp.toList
  }

  /**
   * analyze a string separated by \t, return a list
   */
  val str2ListByTab = str2List("\t")_

  /**
   * analyze a string separated by ",", return a list
   */
  val str2ListByDou = str2List(",")_
}
