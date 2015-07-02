package com.will

import com.will.utils.StringUtil

/**
 * Created by will on 2015/5/27.
 */
object TupeTest {
  def main(args: Array[String]) {
    val t = ("sdfs", 23,"234")
    println(t.+("sdf"))

    val str = "(\"sdfs\", 23,\"234\")".replace("\"", "")
    val substring: String = str.substring(1).substring(0, str.length - 2)
    val list: List[String] = StringUtil.str2ListByDou(substring)
//    list.slice(1, list.length).foreach(println)
    list.drop(1).foreach(println)
    list.foreach(println)
    println(substring)
  }
}
