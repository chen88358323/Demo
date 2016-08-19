package org.util.post;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.util.ocr.asprise.win.AspriseUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by chichen.cc on 2016/1/14.
 */
public class LoginCodeUtil {
    private static final Logger ll = LoggerFactory.getLogger(LoginCodeUtil.class);
private static String destfilename = "D:\\temp\\del\\yc.gif";


    public static String getCode() throws IOException {
    String res=null;
        // 第一步：先下载验证码到本地
        String url = "http://haijia.bjxueche.net/tools/CreateCode.ashx?key=ImgCode&random=0.006666118821571199";
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);

        File file = new File(destfilename);
        if (file.exists()) {
            file.delete();
        }

        HttpResponse response = httpclient.execute(httpget);
        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
        //打印
        showCookies(cookies,"");
        HttpEntity entity = response.getEntity();
        InputStream in = entity.getContent();
        try {
            FileOutputStream fout = new FileOutputStream(file);
            int l = -1;
            byte[] tmp = new byte[2048];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp);
            }
            fout.close();
        } finally {
            in.close();
        }

        httpget.releaseConnection();
//        ll.info("imgCode:" + RegeitTest.testReadGIF(destfilename));
        ll.info("imgCode:" + AspriseUtil.recognize(destfilename));

//
//
//        // 第二步：用Post方法带若干参数尝试登录，需要手工输入下载验证码中显示的字母、数字
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("请输入下载下来的验证码中显示的数字...");
//        String yan = br.readLine();
//
//        HttpPost httppost = new HttpPost("http://www.lashou.com/account/login/");
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("user_id", "testuser007"));
//        params.add(new BasicNameValuePair("pwd", "asdfg123"));
//        params.add(new BasicNameValuePair("yan", yan));
//        params.add(new BasicNameValuePair("save_user", "on"));
//        params.add(new BasicNameValuePair("save_pwd", "on"));
//        params.add(new BasicNameValuePair("sub", "登录"));
//        httppost.setEntity(new UrlEncodedFormEntity(params));
//
//        response = httpclient.execute(httppost);
//        entity = response.getEntity();
//        // 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
//        String postResult = EntityUtils.toString(entity, "GBK");
//        // 我们这里只是简单的打印出当前Cookie值以判断登录是否成功。
//        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
//        for(Cookie cookie: cookies)
//            System.out.println(cookie);
//        httppost.releaseConnection();
//
//
//        // 第三步：打开会员页面以判断登录成功（未登录用户是打不开会员页面的）
//        String memberpage = "http://www.lashou.com/account/orders/";
//        httpget = new HttpGet(memberpage);
//        response = httpclient.execute(httpget); // 必须是同一个HttpClient！
//        entity = response.getEntity();
//        String html = EntityUtils.toString(entity, "GBK");
//        httpget.releaseConnection();
//
//        System.out.println(html);
        return res;
    }
    public static void main(String ads[]){
        ll.info("start testcookie");
        try {
            LoginCodeUtil.getCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/***
 Cookie:
 CNZZDATA5234107=cnzz_eid%3D1288870569-1450422870-http%253A%252F%252Fhaijia.bjxueche.net%252F%26ntime%3D1453192487;
 ASP.NET_SessionId=ai3moasuhpnl51vuynudlj2d;
 ImgCode=rbtEkYdA3Pw=

 * **/
   public static void showCookies(List<Cookie> cl,String name){
       ll.info("*******************"+name+" start *******************");
        if(cl!=null&&cl.size()>0){
            for (int i = 0; i <cl.size() ; i++) {
                Cookie c=cl.get(i);
                ll.info("the " + i + " cookie kv is:" + c.getName() + "||" + c.getValue());
            }
        }else{
            ll.info("no cookies!");
        }
       ll.info("*******************"+name+" end *******************");

   }
    public static boolean queryCookies(List<Cookie> cl,String name){
        boolean res=false;
        if(cl!=null&&cl.size()>0){
            for (int i = 0; i <cl.size() ; i++) {
                Cookie c=cl.get(i);
                if(name.equals(c.getName()))
                    res=true;
            }
        }else{
            ll.info("no cookies!");
        }
        return  res;
    }
    public static void showHeaderes(Header[] hes,String name){
        ll.info("*******************"+name+" start *******************");
        if(hes!=null&&hes.length>0){
            for (int i = 0; i <hes.length ; i++) {
                Header c=hes[i];
                ll.info("the " + i + " header kv is:" + c.getName() + "   " + c.getValue());
            }
        }else{
            ll.info("no Headeres!");
        }
        ll.info("*******************"+name+" end *******************");

    }

}
