package com.autonavi.test.ads.garula;

import com.autonavi.test.ads.garula.model.Beans;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
/**
 * Created by chichen.cc on 2015/7/24.
 * test git
 */
public class SpringJDBCTemplateExample {
    private static final String url="jdbc:mysql://10.101.76.98:9999/cctest?useUnicode=true&characterEncoding=UTF-8";
    private static  final String username="gq5FDS2IgSWqXzTu";
    private  JdbcTemplate jdbcTemplate;

    private static  final String password="xNXmuBr4dvn3BNLLzWZEAerpHqREto";
    @Before
    public void initDatasource() throws SQLException{
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.jdbc.Driver());
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
//批量插入测试代码
    @Test
    public void batchInsertTest() throws InterruptedException {
        //直接批量增加无效
//        String sqlInsert = "INSERT INTO cctest.tb_test(diu,tid ,os ,adcode)"
//                + " VALUES (?, ?, ?, ?)";
//        jdbcTemplate.batchUpdate(sqlInsert, getBeansList(0));

        long start=getCounts();
        List<Object[]> ol=getBeansList(start);
        for (int i = 0; i <ol.size() ; i++) {
            Object[] o=ol.get(i);
            insertTest(o);
            Thread.sleep(3000);
        }
    }

    @Test
    public void insertTest(){

        Object[] b=new Object[4];
        Random r=new Random();
        int tmp=r.nextInt(10010);
        b[0]="tid_"+(tmp);
        b[1]="xxxxxxxxxxx"+tmp;
        b[2]="android";
        b[3]=tmp+"";
        String sqlInsert = "INSERT INTO cctest.tb_test(diu,tid ,os ,adcode)"
                + " VALUES (?,?,?,?)";
        jdbcTemplate.update(sqlInsert,b);
        System.out.print(sqlInsert);
    }
    private long getCounts(){
        String sqlCount="select count(tid) from tb_test";
        Object res=this.jdbcTemplate.query(sqlCount,new RowMapper<Object>() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getObject(1);
            }
        });
        ArrayList r=(ArrayList)res;
        if(r==null||r.size()==0)
            return 0l;
        System.out.println("res is " + r.get(0));
        return (Long)r.get(0);
    }
    private void insertTest(Object[] b){

        String sqlInsert = "INSERT INTO cctest.tb_test(diu,tid ,os ,adcode)"
                + " VALUES (?,?,?,?)";
        int res=jdbcTemplate.update(sqlInsert,b);
        System.out.print(sqlInsert+"  :  "+res);
    }
//    @Test
//    public void insertTest(){
//
//        Object[] b=new Object[4];
//        Random r=new Random();
//        int tmp=r.nextInt(10010);
//        b[0]="tid_"+(tmp);
//        b[1]="xxxxxxxxxxx"+tmp;
//            b[2]="android";
//        b[3]=tmp+"";
//        String sqlInsert = "INSERT INTO cctest.tb_test(diu,tid ,os ,adcode)"
//                + " VALUES (\""+b[0]+"\", \""+b[1]+"\", \""+b[2]+"\", \""+b[3]+"\")";
//        jdbcTemplate.update(sqlInsert);
//        System.out.print(sqlInsert);
//    }
   @Test
   public void queryCountTest(){
       String sqlCount="select count(tid) from tb_test";
//       Object res=jdbcTemplate.queryForObject(sqlCount, Integer.class);
       Object res=this.jdbcTemplate.query(sqlCount,new RowMapper<Object>() {
           public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
               return rs.getObject(1);
           }
       });
       System.out.print("记录条数: " + res);

   }


    @Test
    public void queryLimitTest(){
        String sqlCount="select u_tid from s_amap_dm_alg_user_profiles_all  limit 10000";
//       Object res=jdbcTemplate.queryForObject(sqlCount, Integer.class);
        Object res=this.jdbcTemplate.query(sqlCount,new RowMapper<Object>() {
            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getObject(1);
            }
        });
        ArrayList r=(ArrayList)res;
        int re;
        if(r==null||r.size()==0)
            re=0;
        else
            re=r.size();
        System.out.print("记录条数: "+re );

    }
    @Test
    public void queryListTest(){
        String sqlCount="select tid from tb_test limit 11";

        List<Beans> actors = this.jdbcTemplate.query(
                sqlCount,
                new RowMapper<Beans>() {
                    public Beans mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Beans actor = new Beans();
                        actor.setTid(rs.getString("tid"));
                        return actor;
                    }
                });
        System.out.print("记录条数: "+actors.size());
        if(actors!=null &&actors.size()>0)
        for (int i = 0; i <actors.size(); i++) {
            System.out.println(actors.get(i).getTid());
        }
    }
    private List<Object[]> getBeansList(long i){
        List<Object[]>  bl=new ArrayList<Object[]>(500);
        Object[] b;
        long t=i+500;
        for (long j = i; j < t; j++) {
            b=new Object[4];
            b[0]="tid_"+(j);
            b[1]="xxxxxxxxxxx"+j;
            if(j/2==0)
                b[2]="android";
            else
                b[2]="ios";
            b[3]=j+"";
            bl.add(b);
        }
        return bl;
    }


    public static void main(String[] args)  {




    }

}
