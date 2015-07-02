package com.will.jedis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by will on 2015/6/17.
 */
public class JedisTest {
    public static void  main(String args[]){
        Jedis jedis = new Jedis("127.0.0.1", 6666);
//        Jedis jedis = new Jedis("123.57.84.62", 6379);
        Set<String> keys = jedis.keys("*");
//        for (String key : keys) {
//            System.out.println(key);
//        }
        System.out.println(keys.size());
        jedis.set("name", "will");
        jedis.set("age", "22");

        jedis.close();
    }
}
