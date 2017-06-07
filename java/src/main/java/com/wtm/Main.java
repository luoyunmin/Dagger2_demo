package com.wtm;

/**
 * Created by 王天明 on 2015/12/17 0017.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("开始了");
        Person hero = DaggerPerson.builder().build();
        hero.getCreate().say();
    }
}
