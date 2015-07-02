package com.will

import java.net.URI

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{LocatedFileStatus, RemoteIterator, Path, FileSystem}

/**
 * Created by will on 2015/5/28.
 */
object HDFSUtil {
  def main(args: Array[String]) {
    val baseHdfsUrl = "hdfs://eunke-dp-ana-001:8020"
    val fs: FileSystem = FileSystem.get(URI.create(baseHdfsUrl), new Configuration())
    val path = new Path("/tmptest")
    val files: RemoteIterator[LocatedFileStatus] = fs.listFiles(path, false)
    println(files)
  }
}
