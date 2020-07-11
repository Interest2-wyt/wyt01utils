package com.test.proxy;

import net.sf.cglib.core.DebuggingClassWriter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author : wangyongtao
 * @Description : TODO
 * @Date : 2020/7/10 0010 9:34
 **/
public class MainTest {
    public static void main(String[] args) {
        //新版本 jdk产生代理类
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // VIP 影厅《钢铁侠》
        IronManVipMovie ironManVIPMovie = new IronManVipMovie();
        InvocationHandler invocationHandler = new DynamicInvocationHandler(ironManVIPMovie);
        VipMovie dynamicProxy = (VipMovie) Proxy.newProxyInstance(IronManVipMovie.class.getClassLoader(),
                IronManVipMovie.class.getInterfaces(), invocationHandler);
        dynamicProxy.play();

        // 普通影厅《美国队长》
        CaptionMovie captainAmericaMovie = new CaptionMovie();
        InvocationHandler invocationHandler1 = new DynamicInvocationHandler(captainAmericaMovie);
        Movie dynamicProxy1 = (Movie) Proxy.newProxyInstance(CaptionMovie.class.getClassLoader(),
                CaptionMovie.class.getInterfaces(), invocationHandler1);
        dynamicProxy1.play();
        System.out.println("VIP 影厅《钢铁侠》代理类："+dynamicProxy.getClass());
        System.out.println("普通影厅《美国队长》："+dynamicProxy1.getClass());
    }
}
