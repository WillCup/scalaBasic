package com.will.hadoop;

import org.apache.hadoop.conf.Configuration;

import java.net.URI;
import java.sql.Timestamp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/**
 * Created by will on 2015/5/28.
 */
public class HDFSTests {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(conf);
        FileStatus fileStatus = fs.getFileStatus(new Path("/tmptest"));
        System.out.println("文件路径" + fileStatus.getOwner());
    }
}
