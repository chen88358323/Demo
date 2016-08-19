package org.util.post;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.util.ocr.asprise.win.AspriseUtil;
import org.util.post.parse.HtmlParse;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chichen.cc on 2016/1/19.v
 *
 * http://haijia.bjxueche.net/
 * http://haijia.bjxueche.net/Login.aspx/GetNetText
 */
public class GetUtil {
    private static final Logger ll = LoggerFactory.getLogger(GetUtil.class);

    public static void getLogin(String url) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        httpget.setHeader("Host", "haijia.bjxueche.net");
        httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.setHeader("Accept-Encoding", "gzip, deflate");
        httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");

        httpclient.getParams().setParameter("http.protocol.content-charset", "utf-8");
        HttpResponse response = httpclient.execute(httpget);
        CookieStore cookies = ((AbstractHttpClient)httpclient).getCookieStore();
        //打印
        LoginCodeUtil.showCookies(cookies.getCookies(),"get index ck");
        HttpEntity entity = response.getEntity();
        Map<String ,String> map = getEntityContent(entity);



        Header[] h=response.getAllHeaders();
        //打印
        LoginCodeUtil.showHeaderes(h, "get index headers");

        getCode(httpget, httpclient);

        String imgCode= AspriseUtil.recognize(destfilename);
        imgCode=imgCode.replaceAll(" ","");
        ll.info("yzm:" + imgCode);

        map.put("txtIMGCode", imgCode);

        boolean loginTag=false;
        int count=0;
        do{
            loginTag=login(httpclient,map,h);
            ll.info(" ××××××××××××××××××××××××××× "+count+"次");
            count++;
        }while (!loginTag&&count<4);
        httpget.releaseConnection();
    }
    public static void main(String ads[]){
        ll.info("start testcookie");
        try {
            GetUtil.getLogin("http://haijia.bjxueche.net/Login.aspx");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String printCookie(CookieStore cs){
        String res= cs.toString();
        ll.info("cs "+res);
        return res;

    }

    /****
     * 返回login页面信息
     * 注意返回二进制数据流 (gzip)
     * 需要gzip 解压缩
     * **/
    private static Map<String ,String> getEntityContent(HttpEntity en ){
        Map<String ,String> resMap=null;
        try {
            en = new GzipDecompressingEntity(en);
            String res=EntityUtils.toString(en,"UTF-8" );
            ll.info("login html :"+ res);

            if(StringUtils.isNotBlank(res)){
                Document doc= HtmlParse.getDocByStr(res);
                resMap= HtmlParse. getParams(doc);
//                for (String key : resMap.keySet()) {
//                    String value = resMap.get(key);
//                    ll.info("Key = " + key + ", Value = " + value);
//
//                }
            }

            ll.info("==========================");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resMap;
    }
    private static String destfilename = "D:\\temp\\del\\yc.gif";
    private static byte[] tmp = new byte[2048];
    public static HttpGet getCode(HttpGet httpget, HttpClient httpclient){
        ll.info("***********************get img start*************************");
        String url = "http://haijia.bjxueche.net/tools/CreateCode.ashx?key=ImgCode&random=0.006666118821571199";
        httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httpget.setHeader("Accept-Encoding", "gzip, deflate");
        httpget.setHeader("Accept", "image/png,image/*;q=0.8,*/*;q=0.5");
        httpget.setHeader("Referer", "http://haijia.bjxueche.net/");

        httpget.setURI(URI.create(url));

        File file = new File(destfilename);
        if (file.exists()) {
            file.delete();
        }
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            Header[] h=response.getAllHeaders();
//            Header[] h=httpget.getAllHeaders();
            //打印
            LoginCodeUtil.showHeaderes(h, "get img headers");

            InputStream in = entity.getContent();
            try {
                FileOutputStream fout = new FileOutputStream(file);
                int l = -1;
                while ((l = in.read(tmp)) != -1) {
                    fout.write(tmp);
                }
                fout.close();
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
        //打印
        LoginCodeUtil.showCookies(cookies,"get img cookie");

        ll.info("***********************get img end*************************");

        return httpget;
    }

    public static boolean login(HttpClient httpclient,Map<String ,String> paramsMap,Header[] headers){
       boolean loginTag=false;

        HttpPost httppost = new HttpPost("http://haijia.bjxueche.net/login.aspx");
//        httppost.setHeaders(headers);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (String key : paramsMap.keySet()) {
            String value = paramsMap.get(key);
            ll.info("put Key = " + key + ", Value = " + value+" into params");
            params.add(new BasicNameValuePair(key, value));

        }
        httppost.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        httppost.setHeader("Accept-Encoding", "gzip, deflate");
        httppost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httppost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");

        HttpResponse response = null;
        HttpEntity entity=null;
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params));
            response = httpclient.execute(httppost);

            Header[] h=response.getAllHeaders();
            //打印
            LoginCodeUtil.showHeaderes(h, "get login headers");

            entity = response.getEntity();

            if(entity.getContentEncoding()!=null){
                if("gzip".equalsIgnoreCase(entity.getContentEncoding().getValue())){
                    entity = new GzipDecompressingEntity(entity);
                } else if("deflate".equalsIgnoreCase(entity.getContentEncoding().getValue())){
                    entity = new DeflateDecompressingEntity(entity);
                }
            }
            String res=EntityUtils.toString(entity, "UTF-8");
            ll.info("***** get login =="+res);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CookieStore logincookies = ((AbstractHttpClient)httpclient).getCookieStore();
        //打印
        LoginCodeUtil.showCookies(logincookies.getCookies(), "get login ck");
        loginTag= LoginCodeUtil.queryCookies(logincookies.getCookies(),"LoginOn");
        httppost.releaseConnection();
        return  loginTag;
    }
}
