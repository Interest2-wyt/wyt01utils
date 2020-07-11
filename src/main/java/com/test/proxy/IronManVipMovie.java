package com.test.proxy;

/**
 * @Author : wangyongtao
 * @Description : TODO
 * @Date : 2020/7/10 0010 9:29
 **/
public class IronManVipMovie implements VipMovie{
    @Override
    public void play() {
        System.out.println("钢铁侠");
    }
}
