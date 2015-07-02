package com.will.utils

import java.io.IOException

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import org.json4s._
import org.json4s.jackson.JsonMethods._

/**
 * Created by Administrator on 2015/5/6.
 */
object JsonUtil {

  def assertJsonValid(json: String): JValue = {
    try {
      parse(json)
    } catch {
      case e: JsonParseException => JNothing
      case e: JsonMappingException => JNothing
      case e: IOException => JNothing
    }
  }

  def extract(x: JValue): String = {
    val value = compact(render(x))
    if(value.startsWith("\""))
      value.substring(1, value.length - 1)
    else
      value
  }

  def extractTwice(jValue : JValue, key : String) = {
    val str = extract(jValue)
    extract(assertJsonValid(str.replace("\\", "")) \ key)
  }

}
