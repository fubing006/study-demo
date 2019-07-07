package com.example.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Desc 代理实现
 * 原理：
 * 1.拿到被代理对象的引用，然后获取它的接口
 * 2.JDK代理重新生成一个类，同时实现我们给的代理对象所实现的接口
 * 3.把被代理对象的引用也拿到了
 * 4.重新动态生成一个Class字节码
 * 5.然后编译
 * @Author gzl
 * @Date 2019/7/3
 */
public class MeiPo implements InvocationHandler {

    private Person target;

    public Object getInstance(Person target) throws Exception{
        this.target = target;
        Class clazz = target.getClass();

        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 媒婆做的事，即为代理人做的事
        System.out.println("我是媒婆，你的性别是" + this.target.getSex() + "得给找一个异性");
        System.out.println("====进行海选====");
        this.target.findLove();
        System.out.println("如果合适就准备办事");
        return null;
    }
}
