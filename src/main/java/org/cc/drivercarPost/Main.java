package org.cc.drivercarPost;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.util.DateUtil;
import org.util.JsonUtil;
import org.util.post.PostUtil;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by chichen.cc on 2016/1/21.
 *
 *
 * 查询已经约的车辆
 * http://haijia.bjxueche.net/NetBooking.aspx
 */
public class Main {
    private static final Logger ll = LoggerFactory.getLogger(Main.class);

    static String c1="Cookie: ASP.NET_SessionId=fxvblp4152dzdcs1ly4h3nr2; CNZZDATA5234107=cnzz_eid%3D2146318416-1450422870-http%253A%252F%252Fhaijia.bjxueche.net%252F%26ntime%3D1453373586; CNZZDATA1257130924=652864301-1453373517-%7C1453702790; ImgCode=x6K3gJZEZLA=; LoginOn=z5VjA2RRhbK200GBfLnsGJZiuZf8ntp+y3aJzqws/GOgPi/b4qilbIV5TZXVtFojvNrBahNuFD3sH9gzk7DaFct2ic6sLPxj9jzl6G57APZz2ehuq3NFOhwvsButyqGRK7p5c5eImrC+siiMDIDKaZ/zJby+jow31B9LDp9z8sI1+1iTNJ6uqA7sOuqSCTclzPdJHACGH+M7mzlF1OwtAkYMjRoshEBZU20HutUa4xhklxhC63xGt+M1CgLI+Vi62Omtu9N8N2SqUG9J5J2WJvHLE1XHTD2E56qlnNYi/5lvpV9ViIOG5L+XwlYD0mB5YzpbiQNzJmjY5FNnLSLmkVCYGodLzRZf7OH+KONLG+RQL6kmhgBBpT8Km3KNaJrZPz9xmkOSopDVVthTWtvx1QLCv25cQI3ispTztq1l2o8O+fXBOgp2QlXsMl22snlLIbnMUx9nKXKWL5e3rzS+qL3IBuyMBBLpZ0xYbvVbzroy55xQ/S243mhVw3D0IKb66mdX/XbcQfgTwlxE3tYQgICLcnbzC8bir5RROii50gyXkwi6DW0vkq0xjJ7CYkyOF1FDsxtKJlLmB3GHu1z16GtfXWKMsD0LHjJQg/7UQRk2RO9CBupOdI037sJnda8v";
    static String c2="Cookie: CNZZDATA5234107=cnzz_eid%3D1288870569-1450422870-http%253A%252F%252Fhaijia.bjxueche.net%252F%26ntime%3D1453373587; CNZZDATA1257130924=251588364-1453373517-%7C1453702790; ASP.NET_SessionId=bnosyrdwcgtbm4b2xbwqf1u3; ImgCode=Ci4559gRE/g=; LoginOn=z5VjA2RRhbK200GBfLnsGKUVyAg1xFTsy3aJzqws/GOgPi/b4qilbBI7FoQ1k4w5vNrBahNuFD2BxjHhQVaeGr5+89HDr5IOLz4ZJjAvTHB8N/o/+/kY7thYwBnSDqjJHbRQooZNyTAN10ZWGqgWIwDH8rwNXva4YZCo3ZcMs3eedALkamHo3CzGXSWHCnAApF/9XkSvoBUSLaBR0df0phO37N5J+y6CRJbOLd9H/wPce1DTW10RiIuqPVKCvZH+QY1UNTuY2nUyR3Qm84t7M1bpaYHkrBiRWCAEp3IIgAi5D/wrDyr1iziLZ1ZWW3XDez1t5F2E2u/cW5xJrciJR0YMjRoshEBZzI4IM6SBZ9cySftc1qBeoArVnCFe973Hx21Hm6qFa4Nz4R3BGwS2+4E671mxOI7TQFO5g+B1ToZvN11I6P5i/R2hYcA7TJqxEKCg+XI0gcKRr0gkI1k6UOyRIkSBKzVj5p+2p4UNY5TvxOMk3XkUMklYzw6P6po1yv5kNCd5Xhohp7rfvkppf2kUCCbaeykq4zUKAsj5WLrJm4zWfdvCRapQb0nknZYmkksuxe5+uSgcnMeS2mE2YrkP/CsPKvWLOItnVlZbdcPhxO77gfZgIKdDmubrFu7M";
    /****
     *
     *  设置或获取多个用户cookie
     *
     * **/
    public static List<String> getCookies(){
        List<String> resl=new ArrayList<String>();
        resl.add(c1);
        resl.add(c2);
        return resl;

    }

    /***
     * int[] p = { 34, 21, 54, 18, 23, 76, 38, 98, 45, 33, 27, 51, 11, 20, 79,
     30, 89, 41 };
     * 获取
     * **/
    public static List<String>  getCars(String date){
        List<String> resl;
        String getCarsUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/GetCars";

        String json="{\"yyrq\":\""+date+"\",\"yysd\":\"812\",\"xllxID\":\"2\",\"pageSize\":70,\"pageNum\":1}";
        //post 数据
        String res=PostUtil.postJsonData(getCarsUrl,json,c1);
        ll.info("=============================cars start====================");
        ll.info("res:"+res);
        ll.info("=============================cars end====================");
        resl=JsonUtil.parseCarsList(res);
        //解析结果

        return resl;
    }
    private int keep_alive_time=60;
    private static int cpus=Runtime.getRuntime().availableProcessors();


    //定义缓冲池
    private static Buffer buffer = new Buffer();

    public static void main(String[] args) {
        ExecutorService pools = Executors.newFixedThreadPool(cpus);
        //创建含有两个线程的线程池用于执行从缓冲区读写的操作
        String next= DateUtil.get6days();
        next=next.replace("-","");
        ll.info("date is "+next);
        pools.execute(new ProducerTask(next,"812"));
        List<String> cl=getCookies();
        String[] users={"cc","zym"};
        for (int i = 0; i <cl.size(); i++) {
            pools.execute(new ConsumerTask(cl.get(i),users[i]));
        }

        pools.shutdown();
    }




    //生产者任务类
    private static class ProducerTask implements Runnable {
        private static final Logger ll = LoggerFactory.getLogger(ProducerTask.class);
        /******
         * datestr 为时间 默认为当前运行时间+6天
         * timeline 为约车时间段 812 或者15
         * **/
        ProducerTask(String datestr,String timeline){
            date=datestr;
            tl=timeline;
        }
        private List<String> carlist;
        private String date;
        private String tl;
        public void run() {
            carlist= getCars(date);
            //拼接提交的json信息
            if(carlist!=null&&carlist.size()>0){
                for (int i = 0; i < carlist.size(); i++) {
                    String msg = "{\"yyrq\":\""+date+"\",\"xnsd\":\""+tl+"\",\"cnbh\":\""+carlist.get(i)+"\",\"imgCode\":\"\",\"KMID\":\"2\"}";
                    ll.info("the "+i+msg);
                    buffer.write(msg);
                }
                ll.info("buffer size "+carlist.size());
            }
        }
    }

    //消费者任务类
    private static class ConsumerTask implements Runnable {
        private static final Logger ll = LoggerFactory.getLogger(ConsumerTask.class);
        ConsumerTask(String ck,String threadname){
            this.cookie=ck;
            threadName=threadname;
        }
        private String threadName;
        String bookCarUrl="http://haijia.bjxueche.net/Han/ServiceBooking.asmx/BookingCar";
        private String cookie;
        public void run() {
            while (true) {
                //从缓存区读取整数
                String json=buffer.read();
                ll.info(threadName+"消费者读取 " + json);
                String res=PostUtil.postJsonData(bookCarUrl,json,cookie);
                ll.info(threadName+"返回结果"+res);
                boolean tag = JsonUtil.parseBookRes(res);
                if(tag){
                    ll.info(threadName+"约车成功:"+res);
                    //线程休眠随机时间
                    try {
                        Thread.sleep( 50000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;

                }


            }
        }
    }
    //定义缓存区，用FIFO队列存取数据
    private static class Buffer {

        //缓存区容量
        private static final int CAPACITY = 100;

        //用LinkedList定义FIFO链队
        private java.util.LinkedList<String> queue = new java.util.LinkedList<String>();

        //定义互斥锁
        private static Lock lock = new ReentrantLock();

        //条件：缓存区非空
        private static Condition notEmpty = lock.newCondition();
        //条件：缓存区已满
        private static Condition notFull = lock.newCondition();

        //从缓存区读取整数
        public void write(String v) {
            lock.lock();
            try {
                while (queue.size() == CAPACITY) {
                    System.out.println("等待缓存区未满");
                    notFull.await();
                }

                //缓存区未满条件被唤醒之后，向队列添加
                queue.offer(v);
                //向缓存区非空条件发送信号
                notEmpty.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        @SuppressWarnings("finally")
        public String read() {
            String value = null;
            lock.lock();
            try {
                //当缓存区为空时候：等待非空条件
                while (queue.isEmpty()) {
                    System.out.println("\t\t\t等待唤醒非空条件");
                    notEmpty.await();
                }

                //读取并删除数据
                value = queue.remove();
                //向缓存区未满条件发送信号
                notFull.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
                return value;
            }
        }
    }
}