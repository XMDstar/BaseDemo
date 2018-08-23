package com.zcc.frame;

import java.lang.reflect.Constructor;

public class reflect {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c = Class.forName("com.zcc.frame.inner");
        //Object o = c.newInstance();
        Constructor[] constructors=c.getConstructors();
        System.out.println(inner.name+"==="+constructors.length);
    }

}

class inner {
    public int type;
    private int age;
    private String password;
    public static String name = "zcc";

    static {
        System.out.println("static");
    }

    public inner() {
        System.out.println("inner");
    }

    public void setAge() {
    }

    private void setType() {
    }
}