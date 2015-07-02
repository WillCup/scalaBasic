package com.will.spark.sql

import com.will.utils.DFUitls
import com.will.utils.JsonUtil._
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkContext, SparkConf}
import org.json4s.JsonAST.JNothing
import org.json4s.jackson.JsonMethods._
import org.slf4j.{LoggerFactory, Logger}

/**
 * Created by will on 2015/5/26.
 */

case class Record(key: Int, value: String)

object DataFrameTest {

  val logger: Logger = LoggerFactory.getLogger("will")
  val baseHdfsUrl = "hdfs://eunke-dp-ana-001:8020"
  val url = "jdbc:mysql://10.170.201.62:3306/hairdonkey?user=onlyreader&password=onlyreader@db39qwe"

  def main(args: Array[String]) {
    val sparkConf = new SparkConf().setAppName("Driver Daily Active")
    val sc = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sc)

    import sqlContext.implicits._

//    val data_rdd = sc.textFile(baseHdfsUrl + "/aoplog/donkey-aop.log.2015-05-19")
//      .map(_.substring(16))
//      .map(assertJsonValid(_))
//      .filter(l => l != JNothing)
//      .filter(l =>
//      compact(render(l \ "accessMethod")) != "\"pushInfo\""
//      )
//      .filter(l => compact(render(l \ "auth")).contains("driverId"))


    val willDf = DFUitls.getDataFrameFromJdbc("app_drivers", sqlContext, url)

    println(willDf.where($"id" > 1000).first())



//    val df = sc.parallelize((1 to 100).map(i => Record(i, s"val_$i"))).toDF()
//    // Any RDD containing case classes can be registered as a table.  The schema of the table is
//    // automatically inferred using scala reflection.
//    df.registerTempTable("records")
//
//    // Once tables have been registered, you can run SQL queries over them.
//    println("Result of SELECT *:")
//    sqlContext.sql("SELECT * FROM records").collect().foreach(println)
//
//    // Aggregation queries are also supported.
//    val count = sqlContext.sql("SELECT COUNT(*) FROM records").collect().head.getLong(0)
//    println(s"COUNT(*): $count")
//
//    // The results of SQL queries are themselves RDDs and support all normal RDD functions.  The
//    // items in the RDD are of type Row, which allows you to access each column by ordinal.
//    val rddFromSql = sqlContext.sql("SELECT key, value FROM records WHERE key < 10")
//
//    println("Result of RDD.map:")
//    rddFromSql.map(row => s"Key: ${row(0)}, Value: ${row(1)}").collect().foreach(println)
//
//    // Queries can also be written using a LINQ-like Scala DSL.
//    df.where($"key" === 1).orderBy($"value".asc).select($"key").collect().foreach(println)
//
//    // Write out an RDD as a parquet file.
//    df.saveAsParquetFile("pair.parquet")
//
//    // Read in parquet file.  Parquet files are self-describing so the schmema is preserved.
//    val parquetFile = sqlContext.parquetFile("pair.parquet")
//
//    // Queries can be run using the DSL on parequet files just like the original RDD.
//    parquetFile.where($"key" === 1).select($"value".as("a")).collect().foreach(println)
//
//    // These files can also be registered as tables.
//    parquetFile.registerTempTable("parquetFile")
//    sqlContext.sql("SELECT * FROM parquetFile").collect().foreach(println)

    sc.stop()
  }
}
