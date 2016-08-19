package com.autonavi.test.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by chichen.cc on 2015/8/6.
 */
public class RedisTestMain {
    public static void main(String[] args) {
        testByRedis();
    }

    private static void  testByKVStore(){
        try {
            String host = "10.218.144.33";//控制台显示访问地址
            int port = 6379;
            Jedis jedis = new Jedis(host, port);
            //鉴权信息由用户名:密码拼接而成
            jedis.auth("9d4c85723bec11e5:123456_78a1A");//instance_id:password
            String key = "redis";
            String value = "aliyun-redis";
            //select db默认为0
            jedis.select(1);
            //set一个key
            jedis.set(key, value);
            System.out.println("Set Key " + key + " Value: " + value);
            //get 设置进去的key
            String getvalue = jedis.get(key);
            System.out.println(  " ReturnValue: " + getvalue);
            jedis.quit();
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void testByRedis(){
        try         {
            String host = "10.218.145.189";//控制台显示访问地址
            int port = 6300;
            Jedis jedis = new Jedis(host, port);
            //鉴权信息由用户名:密码拼接而成
//            jedis.auth("9d4c85723bec11e5:123456_78a1A");//instance_id:password
            String key = "redis";
            String value = "aliyun-redis";
            //select db默认为0
            jedis.select(1);
            //set一个key
            jedis.set(key, value);
            System.out.println("Set Key " + key + " Value: " + value);
            //get 设置进去的key
            String getvalue = jedis.get(key);
            System.out.println(  " ReturnValue: " + getvalue);
            jedis.quit();
            jedis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
