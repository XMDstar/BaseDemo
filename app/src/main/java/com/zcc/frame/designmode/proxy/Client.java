package com.zcc.frame.designmode.proxy;

public class Client {
    public static void main(String[] args) {
        Subject man = new Man();
        Proxy proxy = new Proxy(man);
        Subject subject = (Subject) java.lang.reflect.Proxy.newProxyInstance(man.getClass().getClassLoader(), man.getClass().getInterfaces(), proxy);
        subject.shopping();
        System.out.println(subject.getClass().getName());
    }
}
