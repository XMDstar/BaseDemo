package com.zcc.frame.designmode.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Proxy implements InvocationHandler {
    private Object target;

    public Proxy(Subject target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("proxy:" + o.getClass().getName());
        System.out.println("before...");
        method.invoke(target, objects);
        System.out.println("after...");
        return null;
    }
}
