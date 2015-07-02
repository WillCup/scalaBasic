package com.will

import java.io.File

import com.will.utils.JsonUtil._
import junit.framework.Test
import org.json4s.{DefaultFormats, JValue}
import org.scalatest.{Matchers, FlatSpec}

import scala.collection.mutable
import scala.io.Source


/**
 * Created by will on 2015/6/19.
 */
class TestC  extends FlatSpec with Matchers {

  "A Stack" should "pop values in last-in-first-out order" in {
    val stack = new mutable.Stack[Int]
    stack.push(1)
    stack.push(2)
    stack.pop() should be (2)
    stack.pop() should be (1)
  }

  it should "throw NoSuchElementException if an empty stack is popped" in {
    val emptyStack = new mutable.Stack[Int]
    a [NoSuchElementException] should be thrownBy {
      emptyStack.pop()
    }
  }
}

import org.scalatest.FunSuite

class SetSuite extends FunSuite {


  case class Meta(msgType:String, orderId: String)
  case class Action(action : String, operation: String, meta:Meta, time:Long)
  case class Actions(actions : List[Action])

  test("An empty Set should have size 0") {
    assert(Set.empty.size == 0)
  }

  test("Invoking head on an empty Set should produce NoSuchElementException") {
    intercept[NoSuchElementException] {
      Set.empty.head
    }
  }

  def isSame(s : Any, s2:Any) = {
    if (s == null || s2 == null) {
      false
    } else {
      s.toString.equals(s2.toString)
    }
  }

  test("test equal") {
    assert(isSame("1107822", "1107822"))
  }

  test("read file") {
    implicit lazy val formats = DefaultFormats
    val lines = Source.fromFile(new File("D:\\work\\colleges\\liningyu\\actions\\data-example.txt"))
      .getLines()
//    assert(lines.size == 1)
    lines.foreach(line => {
      if (line.length > 17) {
        val json = assertJsonValid(line.substring(17).replace("\\n",",").replace("\\","").replace("\"{","{").replace("}\"","}"))
        val method: String = extract(json \ "accessMethod")
        val request: String = extract(json \ "request")
        val actions: JValue = assertJsonValid(request)
        val actions_j = extract(actions \ "actions")
        println("----?" + actions_j)
        val elements: JValue = assertJsonValid(actions_j)
        val actions1: List[Action] = (actions \ "actions").extract[List[Action]]
        actions1.foreach(l => println(l.action + "\t" + l.meta.orderId))
      } else {
        println("------->" + line)
      }
    })

//    extract(assertJsonValid(extract(assertJsonValid(extract(p \ "request").replace("\\n",",").replace("\\","")
//      .replace("\"{","{").replace("}\"","}")) \ "actions")) \ "action").replace("[","").replace("]","").split(",")
//      .indexOf("\"app.app_launch\""),
//    extract(assertJsonValid(extract(p \ "request").replace("\\n",",").replace("\\","").replace("\"{","{")
//      .replace("}\"","}")) \ "actions"),
//    extract(p \ "date")))
  }
}