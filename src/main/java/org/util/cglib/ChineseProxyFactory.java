package org.util.cglib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by cc on 16-8-17.
 */
public class ChineseProxyFactory {
    public static Chinese getAuthInstance()
    {
        Enhancer en = new Enhancer();
        // 设置要代理的目标类
        en.setSuperclass(Chinese.class);
        // 设置要代理的拦截器
        en.setCallback(new AroundAdvice());
        // 生成代理类的实例
        return (Chinese)en.create();
    }
}
