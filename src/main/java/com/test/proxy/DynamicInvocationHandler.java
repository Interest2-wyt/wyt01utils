package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author : wangyongtao
 * @Description : TODO
 * @Date : 2020/7/10 0010 9:30
 **/
public class DynamicInvocationHandler implements InvocationHandler {
    private Object object;

    public DynamicInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        playStart();
        Object invoke = method.invoke(object, args);
        playEnd();
        return invoke;
    }
    public void playStart() {
        System.out.println("电影开始前正在播放广告");
    }
    public void playEnd() {
        System.out.println("电影结束了，接续播放广告");
    }
}
