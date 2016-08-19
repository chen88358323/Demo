package org.util.cglib;

/**
 * Created by cc on 16-8-17.
 */
public class MainTest {
    public static void main(String[] args)
    {
        Chinese chin = ChineseProxyFactory.getAuthInstance();
        System.out.println(chin.sayHello("孙悟空"));
        chin.eat("西瓜");
        System.out.println(chin.getClass());
    }
}
