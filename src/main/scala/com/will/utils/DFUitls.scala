package com.will.utils

import org.apache.spark.sql.{DataFrame, SQLContext}

/**
 * Created by will on 5/21/15.
 */
object DFUitls {

  /*
   * register table as temporary table, and we can execute sql based on this table.
   *
   */
  def getDataFrameFromJdbc(dbname: String, sqlContext: SQLContext, url : String): DataFrame = {
    val dbDF = sqlContext.load("jdbc", Map(
      "url" -> url,
      "dbtable" -> dbname))
    dbDF.registerTempTable(dbname)
    dbDF
  }

}
