package org.util.post;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chichen.cc on 2015/12/18.
 */
public class PostUtil {
    private static final Logger ll = LoggerFactory.getLogger(PostUtil.class);
   // http://haijia.bjxueche.net/k_right.htm
   public static String url="http://haijia.bjxueche.net/ych2.aspx";
    static String cookie="Cookie: CNZZDATA5234107=cnzz_eid%3D" +
            "1288870569-1450422870-http%253A%252F%252F" +
            "haijia.bjxueche.net%252F%26ntime%3D1452848724; " +
            "ASP.NET_SessionId=byptlx4snr4ppc4lrfdjvdq5; " +
            "ImgCode=P/CVpWwcDhk=; LoginOn=z5VjA2RRhbK200GBf" +
            "LnsGJZiuZf8ntp+y3aJzqws/GOgPi/b4qilbIV5TZXVtFojvNrBahN" +
            "uFD3sH9gzk7DaFct2ic6sLPxj9jzl6G57APZz2ehuq3NFOhwvsButyq" +
            "GRK7p5c5eImrC+siiMDIDKaZ/zJby+jow31B9LDp9z8sI1+1iTNJ6uq" +
            "A7sOuqSCTclzPdJHACGH+M7mzlF1OwtAkYMjRoshEBZU20HutUa4xhk" +
            "lxhC63xGt+M1CgLI+Vi62Omtu9N8N2SqUG9J5J2WJvHLE1XHTD2E56ql" +
            "nNYi/5lvpV9ViIOG5O6PC3SW0KoKA4LcXDpwbPbY5FNnLSLmkVCYGodL" +
            "zRZf7OH+KONLG+RQL6kmhgBBpT8Km3KNaJrZPz9xmkOSopDVVthTWtvx" +
            "1QLCv25cQI3ispTztq1l2o8O+fXBOgp2QlXsMl22snlLIbnMUx9nKXKW" +
            "L5e3rzS+qL3IBuyMBBLpZ0xYbvVbzroy55xQ/S243mhVw3D0IKb66mdX/XbcQfgTwlxE3tYQgICLcnbzC8bir5RROii50gyXkwi6DW0vkq0xjJ7CYkyOF1FDsxtKJlLmB3GHu1z16GtfXWKMsD0LHjJQg/7UQRk2RO9CBupOdI037sJnda8v";

    //    public static String url="http://haijia.bjxueche.net/index.aspx";
    public static String post( String url ) throws ClientProtocolException, IOException {
        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10 * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);

        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(url);

        post.setHeader("Cookie", cookie);
        HttpResponse response = client.execute(post);
        String res= EntityUtils.toString(response.getEntity());
        return res;
    }

    /*****
     * 获取车辆列表
     * {"d":"[\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05106\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05110\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05113\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05118\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05120\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05179\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05182\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05073\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05089\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05208\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05126\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05131\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05134\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05142\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05143\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05072\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05163\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05094\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05115\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05119\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05127\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05146\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05148\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05165\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05187\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05057\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05086\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05153\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05206\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05114\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05116\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05130\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05144\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05154\"\r\n  },\r\n  {\r\n    \"YYRQ\": \"20160122\",\r\n    \"XNSD\": \"812\",\r\n    \"CNBH\": \"05162\"\r\n  }\r\n]_1"}

     * */
    public static String getCarNumsByDay(){
        String res=null;
        String json="{\"yyrq\":\"20160122\",\"yysd\":\"812\",\"xllxID\":\"2\",\"pageSize\":70,\"pageNum\":1}";

        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10 * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);
        String getCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars";
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(getCarsUrl);
            ll.info("parameters:" + json);
                try{
                    StringEntity entity = new StringEntity(json);
                    post.setEntity(entity);
                    post.setHeader("Accept", "application/json");
                    post.setHeader("Content-type", "application/json");

                    long startTime = System.currentTimeMillis();
                    post.setHeader("Cookie",cookie);
                    //设置编码  
                    HttpResponse response=client.execute(post);
                    long endTime = System.currentTimeMillis();
                    int statusCode = response.getStatusLine().getStatusCode();
                    ll.info("statusCode:" + statusCode);
                    ll.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
                    if(statusCode != HttpStatus.SC_OK){
                        ll.error("Method failed:"+response.getStatusLine());
                    }
                    //Read the response body
                    String body=EntityUtils.toString(response.getEntity());
                    ll.info("====>"+body);
                }catch(IOException e){
                    //发生网络异常
                    ll.error("exception occurred!\n"+e);
                }
        return res;
    }

    private void parseCarNumsJson(String json){
        json="{\"d\":\"[\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05106\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05110\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05113\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05118\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05120\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05179\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05182\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05073\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05089\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05208\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05126\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05131\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05134\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05142\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05143\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05072\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05163\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05094\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05115\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05119\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05127\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05146\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05148\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05165\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05187\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05057\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05086\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05153\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05206\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05114\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05116\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05130\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05144\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05154\\\"\\r\\n  },\\r\\n  {\\r\\n    \\\"YYRQ\\\": \\\"20160122\\\",\\r\\n    \\\"XNSD\\\": \\\"812\\\",\\r\\n    \\\"CNBH\\\": \\\"05162\\\"\\r\\n  }\\r\\n]_1\"}\n";
    }
    public static String bookCarNumsByDay(){
        String res=null;
        String json="{\"yyrq\":\"20160122\",\"xnsd\":\"15\",\"cnbh\":\"05206\",\"imgCode\":\"\",\"KMID\":\"2\"}";

        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10 * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);
        String getCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/BookingCar";
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(getCarsUrl);
        ll.info("parameters:" + json);
        try{
            StringEntity entity = new StringEntity(json);
            post.setEntity(entity);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            long startTime = System.currentTimeMillis();
            post.setHeader("Cookie",cookie);
            //设置编码
            HttpResponse response=client.execute(post);
            long endTime = System.currentTimeMillis();
            int statusCode = response.getStatusLine().getStatusCode();
            ll.info("statusCode:" + statusCode);
            ll.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
            if(statusCode != HttpStatus.SC_OK){
                ll.error("Method failed:"+response.getStatusLine());
            }
            //Read the response body
            String body=EntityUtils.toString(response.getEntity());
            ll.info("====>"+body);
        }catch(IOException e){
            //发生网络异常
            ll.error("exception occurred!\n"+e);
        }
        return res;

    }

    /***
     * 提交制定的json数据
     * @param url
     * @param  json 提交数据
     * @param user  对应用户cookie
     * */
    public static String postJsonData(String url,String json,String user){
        String res=null;

        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 10 * 1000);
        HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);
        HttpPost post = new HttpPost(url);
        ll.info("parameters:" + json);
        try{
            StringEntity entity = new StringEntity(json);
            post.setEntity(entity);
            post.setHeader("Accept", "application/json");
            post.setHeader("Content-type", "application/json");

            long startTime = System.currentTimeMillis();
            post.setHeader("Cookie",user);
            //设置编码
            HttpResponse response=client.execute(post);
            long endTime = System.currentTimeMillis();
            int statusCode = response.getStatusLine().getStatusCode();
            ll.info("statusCode:" + statusCode);
            ll.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
            if(statusCode != HttpStatus.SC_OK){
                ll.error("Method failed:"+response.getStatusLine());
            }
            //Read the response body
            res=EntityUtils.toString(response.getEntity());
            ll.info("====>"+res);
        }catch(IOException e){
            //发生网络异常
            ll.error("exception occurred!\n"+e);
        }finally {
            post.releaseConnection();
            return res;
        }
    }
/***
 *
 *
 CNZZDATA5234107=cnzz_eid%3D1288870569-1450422870-http%253A%252F%252Fhaijia.bjxueche.net%252F%26ntime%3D1452755910; ASP.NET_SessionId=dsz3p5iovkxtjizt0kcakq5d; ImgCode=tQ/oM2UmSsI=; LoginOn=z5VjA2RRhbK200GBfLnsGJZiuZf8ntp+y3aJzqws/GOgPi/b4qilbIV5TZXVtFojvNrBahNuFD3sH9gzk7DaFct2ic6sLPxj9jzl6G57APZz2ehuq3NFOhwvsButyqGRK7p5c5eImrC+siiMDIDKaZ/zJby+jow31B9LDp9z8sI1+1iTNJ6uqA7sOuqSCTclzPdJHACGH+M7mzlF1OwtAkYMjRoshEBZU20HutUa4xhklxhC63xGt+M1CgLI+Vi62Omtu9N8N2SqUG9J5J2WJvHLE1XHTD2E56qlnNYi/5lvpV9ViIOG5Fw2XVM3GG1wFm9wrz4Zh2HY5FNnLSLmkVCYGodLzRZf7OH+KONLG+RQL6kmhgBBpT8Km3KNaJrZPz9xmkOSopDVVthTWtvx1QLCv25cQI3ispTztq1l2o8O+fXBOgp2QlXsMl22snlLIbnMUx9nKXKWL5e3rzS+qL3IBuyMBBLpZ0xYbvVbzroy55xQ/S243mhVw3D0IKb66mdX/XbcQfgTwlxE3tYQgICLcnbzC8bir5RROii50gyXkwi6DW0vkq0xjJ7CYkyOF1FDsxtKJlLmB3GHu1z16G+lX1WIg4bkwqMNRB13mwGdsGA0RvQA1o037sJnda8v
 * */
    public static String getHtmlBySelenium(String url){
        System.getProperties().setProperty("webdriver.chrome.driver", "D:\\SOFT\\selenium\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.get(url);
        WebElement webElement = webDriver.findElement(By.xpath("/html"));
        String res=webElement.getAttribute("outerHTML");
        System.out.println(res);
        try {
            res = post(url);

            System.out.println(res+"");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        webDriver.close();
        return res;
    }

    public static void main(String ads[]){
        String res= null;
//        try {
//            res = post(url);
//
//            ll.info(res+"");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
        //获取可约车辆列表
//        getCarNumsByDay();
        //约车
//        bookCarNumsByDay();

//        res= getHtmlBySelenium(url);
        ll.info(cookie);
    }
}
