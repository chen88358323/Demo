package com.autonavi.test.redis;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by chichen.cc on 2015/8/6.
 */
@ContextConfiguration(locations = {"classpath*:spring_redis.xml"})
public class RedisTest extends AbstractJUnit4SpringContextTests {

//    @Autowired
//    private IUserDao userDao;
//
//    /**
//     * 新增
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testAddUser() {
//        User user = new User();
//        user.setId("user1");
//        user.setName("java2000_wl");
//        boolean result = userDao.add(user);
//        Assert.assertTrue(result);
//    }
//
//    /**
//     * 批量新增 普通方式
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testAddUsers1() {
//        List<User> list = new ArrayList<User>();
//        for (int i = 10; i < 50000; i++) {
//            User user = new User();
//            user.setId("user" + i);
//            user.setName("java2000_wl" + i);
//            list.add(user);
//        }
//        long begin = System.currentTimeMillis();
//        for (User user : list) {
//            userDao.add(user);
//        }
//        System.out.println(System.currentTimeMillis() - begin);
//    }
//
//    /**
//     * 批量新增 pipeline方式
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testAddUsers2() {
//        List<User> list = new ArrayList<User>();
//        for (int i = 10; i < 1500000; i++) {
//            User user = new User();
//            user.setId("user" + i);
//            user.setName("java2000_wl" + i);
//            list.add(user);
//        }
//        long begin = System.currentTimeMillis();
//        boolean result = userDao.add(list);
//        System.out.println(System.currentTimeMillis() - begin);
//        Assert.assertTrue(result);
//    }
//
//    /**
//     * 修改
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testUpdate() {
//        User user = new User();
//        user.setId("user1");
//        user.setName("new_password");
//        boolean result = userDao.update(user);
//        Assert.assertTrue(result);
//    }
//
//    /**
//     * 通过key删除单个
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testDelete() {
//        String key = "user1";
//        userDao.delete(key);
//    }
//
//    /**
//     * 批量删除
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testDeletes() {
//        List<String> list = new ArrayList<String>();
//        for (int i = 0; i < 10; i++) {
//            list.add("user" + i);
//        }
//        userDao.delete(list);
//    }
//
//    /**
//     * 获取
//     * <br>------------------------------<br>
//     */
//    @Test
//    public void testGetUser() {
//        String id = "user1";
//        User user = userDao.get(id);
//        Assert.assertNotNull(user);
//        Assert.assertEquals(user.getName(), "java2000_wl");
//    }
//
//    /**
//     * 设置userDao
//     * @param userDao the userDao to set
//     */
//    public void setUserDao(IUserDao userDao) {
//        this.userDao = userDao;
//    }



    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSpringRedis() {
        String res;
        boolean rb;
        AtomicInteger i=new AtomicInteger();
                ArrayList al =new ArrayList();
            // String读写
            stringRedisTemplate.delete("myStr");
        rb=  stringRedisTemplate.opsForValue().setIfAbsent("myStr", "http://yjmyzz.cnblogs.com/");
        System.out.println("---------------1 "+rb);
        rb= stringRedisTemplate.opsForValue().setIfAbsent("myStr", "http://yjmyzz.cnblogs.com/");
        System.out.println("---------------2 " + rb);
            System.out.println(stringRedisTemplate.opsForValue().get("myStr"));
            System.out.println("---------------");


        stringRedisTemplate.delete("myStr");

    //            // List读写
//            stringRedisTemplate.delete("myList");
//            stringRedisTemplate.opsForList().rightPush("myList", "A");
//            stringRedisTemplate.opsForList().rightPush("myList", "B");
//            stringRedisTemplate.opsForList().leftPush("myList", "0");
//            List<String> listCache = stringRedisTemplate.opsForList().range(
//                    "myList", 0, -1);
//            for (String s : listCache) {
//                System.out.println(s);
//            }
//            System.out.println("---------------");
//
//            // Set读写
//            stringRedisTemplate.delete("mySet");
//            stringRedisTemplate.opsForSet().add("mySet", "A");
//            stringRedisTemplate.opsForSet().add("mySet", "B");
//            stringRedisTemplate.opsForSet().add("mySet", "C");
//            Set<String> setCache = stringRedisTemplate.opsForSet().members(
//                    "mySet");
//            for (String s : setCache) {
//                System.out.println(s);
//            }
//            System.out.println("---------------");
//
//            // Hash读写
//            stringRedisTemplate.delete("myHash");
//            stringRedisTemplate.opsForHash().put("myHash", "PEK", "北京");
//            stringRedisTemplate.opsForHash().put("myHash", "SHA", "上海虹桥");
//            stringRedisTemplate.opsForHash().put("myHash", "PVG", "浦东");
//            Map<Object, Object> hashCache = stringRedisTemplate.opsForHash()
//                    .entries("myHash");
//            for (Map.Entry<Object, Object> entry : hashCache.entrySet()) {
//                System.out.println(entry.getKey() + " - " + entry.getValue());
//            }
//
//            System.out.println("---------------");


    }
}