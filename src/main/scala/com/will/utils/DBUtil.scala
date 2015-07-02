package com.will.utils
import java.sql.{Connection, DriverManager, ResultSet};
/**
 * Created by will on 2015/5/27.
 */
object DBUtil {
  def main(args: Array[String]) {
    // create database connection
    val conn_str = "jdbc:mysql://192.168.1.70:3306/bi?user=bi&password=bi"

    // Load the driver
    Class.forName("com.mysql.jdbc.Driver")

    // Setup the connection
    val conn = DriverManager.getConnection(conn_str)
    try {
      // Configure to be Read Only
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)

      // Execute Query
      val rs = statement.executeQuery("SELECT count(id) FROM daily_action_owner")

      // Iterate Over ResultSet
      while (rs.next) {
        println(rs.getInt(1))
      }
    }
    finally {
      conn.close
    }
  }
}
