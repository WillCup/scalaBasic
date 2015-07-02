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
   * analyze a string separated by \t, return a list
   * @param str
   * @return
   */
  def str2List(str :String): List[String] = {
    val tmp = new ListBuffer[String]()
//    var count:Int = 0
    for (s <- str.split("\t")) {
      tmp.append(s)
//      println(s)
//      count +=1
//      println("count is " + count)
    }
    tmp.toList
  }

}
