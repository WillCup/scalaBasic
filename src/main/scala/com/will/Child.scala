package com.will

import com.will.abs.AbstractOne

/**
 * Created by will on 2015/7/1.
 */
object Child {
  def main(args: Array[String]) {
    val c = new Child()
    c.test()
    c.test2()
  }
}
class Child extends AbstractOne {
  def test2(): Unit = {
    print("sdfsdfd")
  }

  override def test(): Unit = {
    print("new")
  }
}
