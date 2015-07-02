package com.will.basic

/**
 * Created by will on 2015/6/15.
 */
object AppChild extends App {
  new Thread(new AppChild("fuck")).start;
}

abstract class AppParent(anotherName : String){
  val name : String
  def personMethod(): Unit = {
    println("This is  " + name + "'s personal method")
  }

  def method2() = {
    println("another name will be used here" + anotherName)
  }
}

class AppChild(a : String) extends AppParent(a) with Runnable {
  override def run(): Unit = {
    println("I am just a thread, and My name is : " + name)
    cry(a)
  }

  override val name: String = "test"

  def cry(p : String) = {
    println(p + " is crying...");
  }
}

